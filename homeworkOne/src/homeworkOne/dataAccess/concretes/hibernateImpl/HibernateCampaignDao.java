package homeworkOne.dataAccess.concretes.hibernateImpl;

import java.util.ArrayList;
import java.util.List;

import homeworkOne.dataAccess.abstracts.ICampaignDao;
import homeworkOne.entities.concretes.Campaign;
import homeworkOne.entities.concretes.StarbucksCampaign;

public class HibernateCampaignDao implements ICampaignDao {

	@Override
	public void campaignDetails(Campaign campaign) 
	{
		System.out.println("Hibernate kullanildi.");
		System.out.println(campaign.getStoreName() + " isimli dukkanda " + campaign.getCampaignName() + " isimli kampanya kullanildi.");
	}

	@Override
	public void add(Campaign campaign) 
	{
		System.out.println("Hibernate kullanilarak kampanya eklendi.");
	}

	@Override
	public void update(Campaign campaign) 
	{
		System.out.println("Hibernate kullanilarak kampanya guncellendi.");
	}

	@Override
	public void delete(Campaign campaign) 
	{
		System.out.println("Hibernate kullanilarak kampanya silindi.");
	}

	@Override
	public List<Campaign> getAll() 
	{
		Campaign campaign = new StarbucksCampaign(1);
		
		List<Campaign> campaigns = new ArrayList<Campaign>();
		
		campaigns.add(campaign);
		
		System.out.println("Hibernate kullanildi.");
		
		return campaigns;
	}

}
