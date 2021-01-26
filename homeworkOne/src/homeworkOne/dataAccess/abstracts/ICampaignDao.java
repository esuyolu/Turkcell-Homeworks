package homeworkOne.dataAccess.abstracts;

import homeworkOne.entities.concretes.Campaign;

public interface ICampaignDao extends IEntityRepository<Campaign> {
	public void campaignDetails(Campaign campaign);
}
