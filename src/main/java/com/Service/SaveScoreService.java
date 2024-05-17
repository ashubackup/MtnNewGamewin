package com.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Entity.TblAirtime;
import com.Entity.TblCashRedeem;
import com.Entity.TblSaveScore;
import com.Entity.Tbl_Player;
import com.Modal.SaveScoreRequest;
import com.Repository.AirtimeRepo;
import com.Repository.PlayerRepository;
import com.Repository.TblCashRedeemRepo;
import com.Repository.TblScoreRepo;

@Component
public class SaveScoreService 
{
	@Autowired
	TblScoreRepo scoreRepo;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	TblCashRedeemRepo cashRedeemRepo;

	public String scoreService(SaveScoreRequest request)
	{
		String response = "Failed";
		try
		{
			Tbl_Player player = playerRepository.findByIdAndGameid(Integer.parseInt(request.getUserid()),request.getGameId());
			TblSaveScore saveScore = new TblSaveScore();
			saveScore.setAni(player.getMsisdn());
			saveScore.setGameId("1");
			saveScore.setScore(request.getScore());
			saveScore.setDatetime(LocalDateTime.now());
			scoreRepo.save(saveScore);
			response="Success";
			System.out.println("Score Saved:::");
			
			saveAirtimeData(player.getMsisdn(),"1",request.getScore(),request.getUserid());
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
	
	@Autowired
	AirtimeRepo airtimeRepo;
	
	public void saveAirtimeData(String msisdn,String gameId,String score,String userId)
	{
		try
		{
			TblAirtime airtime = airtimeRepo.findByMsisdnAndGameId(msisdn, gameId);
			if(airtime==null)
			{
				TblAirtime newAirtimeUser = new  TblAirtime();
				newAirtimeUser.setDatetime(LocalDateTime.now());
				newAirtimeUser.setGameId(gameId);
				newAirtimeUser.setMsisdn(msisdn);
				newAirtimeUser.setStatus("0");
				newAirtimeUser.setScore(score);
				airtimeRepo.save(newAirtimeUser);
				System.out.println("Airtime Data Added");
				cashData(msisdn, gameId, score,userId);
			}
			else
			{
				String oldscore = airtime.getScore();
				airtime.setScore(String.valueOf(Integer.parseInt(oldscore)+Integer.parseInt(score)));
				airtimeRepo.save(airtime);
				cashData(msisdn, gameId, score,userId);
				System.out.println("Airtime Data Addedd");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void cashData(String msisdn,String gameId,String score,String userId)
	{
		try
		{
			TblCashRedeem cashRedeem = cashRedeemRepo.findByAniAndGameId(msisdn, gameId);
			if(cashRedeem==null)
			{
				TblCashRedeem newCashUser = new TblCashRedeem();
				newCashUser.setAni(msisdn);
				newCashUser.setCheckStatus("0");
				newCashUser.setGameid(gameId);
				newCashUser.setDatetime(LocalDateTime.now());
				newCashUser.setModifiedDatetime(LocalDateTime.now());
				newCashUser.setStatus("0");
				newCashUser.setUserid(userId);
				newCashUser.setCoins(Integer.parseInt(score));
				cashRedeemRepo.save(newCashUser);
				System.out.println("Cash Data Added for new User :::");
			}
			else 
			{
				Integer oldCoin=cashRedeem.getCoins();
				Integer newTotalCoins = oldCoin+cashRedeem.getCoins();
				cashRedeem.setCoins(newTotalCoins);
				cashRedeem.setModifiedDatetime(LocalDateTime.now());
				cashRedeemRepo.save(cashRedeem);
				System.out.println("Cash Data Updated ::::");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
