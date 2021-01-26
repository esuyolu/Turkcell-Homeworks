package homeworkOne.business.concretes;

import java.util.List;

import homeworkOne.dataAccess.abstracts.ICampaignDao;
import homeworkOne.entities.concretes.Campaign;

public class CampaignService {
	private ICampaignDao campaignDao;

	public CampaignService(ICampaignDao campaignDao) 
	{
		this.campaignDao = campaignDao;
	}
	
	public void campaignDetails(Campaign campaign)
	{
		campaignDao.campaignDetails(campaign);
	}
	
	public void add(Campaign campaign)
	{
		campaignDao.add(campaign);
	}
	
	public void update(Campaign campaign)
	{
		campaignDao.update(campaign);
	}
	
	public void delete(Campaign campaign)
	{
		campaignDao.delete(campaign);
	}
	
	public List<Campaign> getAll() 
	{
		return campaignDao.getAll();
	}
}
