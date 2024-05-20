package com.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Entity.TblGamesData;
import com.Repository.TblGamesDataRepo;

@Service
public class SendTermsService 
{
	@Autowired
	private TblGamesDataRepo gamesDataRepo;
	
	public String sendTermsService(String gameId)
	{
		try
		{
			TblGamesData gamesData 
			= gamesDataRepo.findByStatusAndGameId("1",Integer.parseInt(gameId.trim()));
			
			return gamesData.getTerms();
		}catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}