package com.example.turkcell.business.concretes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.turkcell.business.abstracts.ICustomerService;
import com.example.turkcell.entities.abstracts.SubscriptionType;
import com.example.turkcell.entities.concretes.Credit;
import com.example.turkcell.entities.concretes.Customer;
import com.example.turkcell.entities.concretes.Invoice;
import com.example.turkcell.entities.concretes.Payment;
import com.example.turkcell.repositories.CreditRepository;
import com.example.turkcell.repositories.CustomerRepository;
import com.example.turkcell.repositories.InvoiceRepository;
import com.example.turkcell.repositories.PaymentRepository;

@Service
public class CustomerManager implements ICustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private CreditRepository creditRepository;

	@Override
	public List<Customer> findAll() 
	{
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> findById(int id) 
	{
		return customerRepository.findById(id);
	}

	@Override
	public Customer save(Customer customer) 
	{
		return customerRepository.save(customer);
	}

	@Override
	public void delete(Customer customer) 
	{
		customerRepository.delete(customer);
	}
	
	@Override
	public List<Credit> creditApplication(int customerId)
	{
		List<Credit> credits = creditRepository.findCreditsByCustomerId(customerId);
		
		int size = credits.size();
		
		Credit creditNew = new Credit();
		
		for (Credit credit : credits) {
				
			if (validationForCredit(customerId).containsValue(true)) {
						
				if (credit == null)
					creditApplicationForFirstTime(customerId);
				
				if (credit.getDateCreditTake().plusMonths(1).isBefore(LocalDate.now()))
					;
				
				creditNew.setId(++size);
				creditNew.setDateCreditTake(LocalDate.now());
				creditNew.setDateCreditRepayment(LocalDate.now().plusMonths(1));
				creditNew.setMaxMoney(countMaxMoney(customerId));
				creditNew.setCreditApplicationPerMonth(true);
				creditNew.setCreditLimitPerYear(countCreditLimitPerYear(customerId));
				creditNew.setCustomerId(customerId);
			}
		}
		
		creditRepository.save(creditNew);
		
		List<Credit> updatedCredits = creditRepository.findCreditsByCustomerId(customerId);
		
		return updatedCredits;
	}
	
	private List<Credit> creditApplicationForFirstTime(int customerId)
	{
		List<Credit> credits = creditRepository.findCreditsByCustomerId(customerId);
		
		int size = credits.size();
		
		Credit creditNew = new Credit();
		
		creditNew.setId(++size);
		creditNew.setDateCreditTake(LocalDate.now());
		creditNew.setDateCreditRepayment(LocalDate.now().plusMonths(1));
		creditNew.setMaxMoney(countMaxMoney(customerId));
		creditNew.setCreditApplicationPerMonth(true);
		creditNew.setCreditLimitPerYear(countCreditLimitPerYear(customerId));
		creditNew.setCustomerId(customerId);
				
		creditRepository.save(creditNew);
		
		List<Credit> updatedCredits = creditRepository.findCreditsByCustomerId(customerId);
		
		return updatedCredits;
	}

	
	private Map<String, Boolean> validationForCredit(int customerId)
	{
		
		Map<String, Boolean> response = new HashMap<>();
		
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		LocalDate now = LocalDate.now();
		LocalDate dateOfSubscription = customer.get().getDateOfSubscription();
		
		long year = ChronoUnit.YEARS.between(now, dateOfSubscription);
		
		if (customer.get().getSubscriptionType().equals(SubscriptionType.TURKCELL)) {
			
			if (year < 1)
				response.put("kredi basvurusunda bulunabilmeniz icin 1 yila askin sure Turkcell abonesi olmanız gereklidir", Boolean.FALSE);
			else {
				validationPaymentsOfInvoices(customerId);
				response.put("kredi basvurunuz alinmistir", Boolean.TRUE);
			}
		}
		else {
			response.put("kredi basvurusunda bulunmaniz icin Turkcell abonesi olmaniz gerekir", Boolean.FALSE);
			subscribeToTurkcell(customerId);
		}
		
		return response;
	}
	
	private Map<String, Boolean> validationPaymentsOfInvoices(int customerId)
	{
		Map<String, Boolean> response = new HashMap<>();
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		int posCount = 0;
		int negCount = 0;
		
		List<Integer> invoicesIds = invoiceRepository.findInvoicesIdsByCustomerId(customerId);
		
		for (int invoiceId : invoicesIds) {
			Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
			List<Integer> paymentsIds = paymentRepository.findPaymnetsIdsIdsByInvoiceId(invoiceId);

			
			for (int paymentId : paymentsIds) {
				Optional<Payment> payment = paymentRepository.findById(paymentId);
				
				if (payment.get().getPaymentAmount().compareTo(invoice.get().getInvoiceCost()) < 0
						|| payment.get().getPaymentDate().isAfter(invoice.get().getInvoiceDueDate()))
					++negCount;
				else
					++posCount;
			}
		}
		
		if (negCount > posCount) {
			customer.get().setBlackListed(true);
			response.put("cok uzgunuz! sartları saglayamadiginiz icin kredi basvurunuz reddedildi", Boolean.FALSE);
		}
		else
			response.put("tebrikler! kredi basvurunuz onaylandi", Boolean.TRUE);
		
		return response;
	}
	
	private BigDecimal countCreditLimitPerYear(int customerId)
	{
		return countMaxMoney(customerId).multiply(BigDecimal.valueOf(12));
	}
	
	private BigDecimal countMaxMoney(int customerId)
	{
		BigDecimal sumOfPayments = BigDecimal.ZERO;
		BigDecimal result = BigDecimal.ZERO;
		int count = 0;

		
		List<Integer> invoicesIds = invoiceRepository.findInvoicesIdsByCustomerId(customerId);
		
		for (int invoiceId : invoicesIds) {
			List<Integer> paymentsIds = paymentRepository.findPaymnetsIdsIdsByInvoiceId(invoiceId);
			
			count = paymentsIds.size();
			
			for (int paymentId : paymentsIds) {
				Optional<Payment> payment = paymentRepository.findById(paymentId);
				
				sumOfPayments = sumOfPayments.add(payment.get().getPaymentAmount()); 
			}
		}	
		
		result = sumOfPayments.divide(BigDecimal.valueOf(count));
		
		return result;
	}
	
	private void subscribeToTurkcell(int customerId)
	{
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		customer.get().setDateOfSubscription(LocalDate.now());
		customer.get().setSubscriptionType(SubscriptionType.TURKCELL);
		customer.get().setBlackListed(false);
	}
}
