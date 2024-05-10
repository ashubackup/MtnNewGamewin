package com.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblSubscription;
import com.Entity.Tbl_Player;
import com.Modal.SubRequest;
import com.Repository.PlayerRepository;
import com.Repository.TblSubRepo;

@Service
public class CheckSubService 
{

	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private TblSubRepo subRepo;
	
	
	public String checkSub(SubRequest request)
	{
		String result="";
		try
		{
			//Getting Request
			System.out.println("Request is "+request);
			
			//Changes After New Portal Implemented
			String ani="";
			
			
			Tbl_Player playerAni = playerRepo.findByIdAndGameid(request.getUserId(), request.getGameId());
			
//			
//			if(request.getGameId().equalsIgnoreCase("1")||request.getGameId().equalsIgnoreCase("11"))
//			{
//				//i.e for New Bigcash Portals on MTN & on Telkom
//				ani=request.getUserId().toString();
////				ani = playerAni.getId().toString();
//				
//			}
			
				//Checking Request
				Tbl_Player player = playerRepo.findByIdAndGameid(playerAni.getId(),request.getGameId());
				System.out.println("ani is "+player.getMsisdn());
				ani=player.getMsisdn();
			
			
			//Hit on YD Api
			result = checkUserState(ani,request.getGameId());
			return result;
		}catch(Exception e)
		{
			e.printStackTrace();
			return result;
		}
	}	
	
	public String checkUserState(String ani,String gameId)
	{
		String result="0";
		try
		{
			TblSubscription subscription = subRepo.findByMsisdn(ani);
			
			if(subscription==null)
			{
				result="0";
			}
			else if(subscription.getNext_billed_date().isAfter(LocalDateTime.now()))
			{
				result="1";
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return result;
	}
}
