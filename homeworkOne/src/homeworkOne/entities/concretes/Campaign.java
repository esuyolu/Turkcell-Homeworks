package homeworkOne.entities.concretes;

import homeworkOne.entities.abstracts.IEntity;

public class Campaign implements IEntity {
	private int id;
	private String campaignName;
	private String storeName;
	
	public Campaign()
	{}
	
	public Campaign(int id, String campaignName, String storeName) 
	{
		this.id = id;
		this.campaignName = campaignName;
		this.storeName = storeName;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getCampaignName() 
	{
		return campaignName;
	}

	public void setCampaignName(String campaignName) 
	{
		this.campaignName = campaignName;
	}

	public String getStoreName() 
	{
		return storeName;
	}

	public void setStoreName(String storeName) 
	{
		this.storeName = storeName;
	}
}
