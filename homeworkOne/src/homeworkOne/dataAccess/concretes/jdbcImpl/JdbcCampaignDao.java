package homeworkOne.dataAccess.concretes.jdbcImpl;

import java.util.ArrayList;
import java.util.List;

import homeworkOne.dataAccess.abstracts.ICampaignDao;
import homeworkOne.entities.concretes.Campaign;

public class JdbcCampaignDao implements ICampaignDao {

	@Override
	public void campaignDetails(Campaign campaign) 
	{
		System.out.println("JDBC kullanildi.");
		System.out.println(campaign.getStoreName() + " isimli dukkanda " + campaign.getCampaignName() + " isimli kampanya kullanildi.");
	}

	@Override
	public void add(Campaign campaign) 
	{
		System.out.println("JDBC kullanilarak kampanya eklendi.");
	}

	@Override
	public void update(Campaign campaign) 
	{
		System.out.println("JDBC kullanilarak kampanya guncellendi.");
	}

	@Override
	public void delete(Campaign campaign) 
	{
		System.out.println("JDBC kullanilarak kampanya silindi.");
	}

	@Override
	public List<Campaign> getAll() 
	{
		Campaign campaign = new Campaign(1, "starbucks yildiz kampanyasi", "Starbucks");
		
		List<Campaign> campaigns = new ArrayList<Campaign>();
		
		campaigns.add(campaign);
		
		System.out.println("JDBC kullanildi.");
		
		return campaigns;
	}
}
