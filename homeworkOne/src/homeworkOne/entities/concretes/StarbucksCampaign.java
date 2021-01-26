package homeworkOne.entities.concretes;

public class StarbucksCampaign extends Campaign {

	public StarbucksCampaign(int id, String campaignName, String storeName) 
	{
		super(id, campaignName, storeName);
		System.out.println("bir *(yildiz) kazandiniz.");
	}
	
	public StarbucksCampaign(int id) 
	{
		this(1, "Starbucks yildiz kampanyasi", "Starbucks");
	}
}
