package com.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblCallBack;
import com.Entity.TblSubscription;
import com.Entity.Tbl_Player;
import com.Entity.Tbl_dlr;
import com.Repository.CallBackRepo;
import com.Repository.PlayerRepository;
import com.Repository.TblDlrRepo;
import com.Repository.TblSubRepo;

@Service
public class CallBackService 
{
	
	@Autowired
	CallBackRepo callBackRepo;
	
	@Autowired
	TblDlrRepo dlrRepo;
	
	@Autowired
	TblSubRepo subRepo;
	
	@Autowired
	PlayerRepository playerRepository;

	public Map<String,String> callBack(Map<String,String> json)
	{
		Map<String,String> response=new HashMap<String, String>();
		response.put("response", "failed");
		response.put("result", "2");
		try
		{
			
//			TblCallBack tblCallBack = new TblCallBack();
//			tblCallBack.setCallback(json.toString());
//			tblCallBack.setDatetime(LocalDateTime.now());
//			tblCallBack.setStatus("0");
//			callBackRepo.save(tblCallBack);
//			System.out.println("CallBack"+json);
//			System.out.println("CallBack Saved");
			String type=json.get("action").toString();
			String ani = json.get("msisdn").toString();
			Tbl_dlr dlr = new Tbl_dlr();
			dlr.setAction(json.get("action").toString());
			dlr.setCharge(json.get("charge").toString());
			dlr.setDate(LocalDateTime.now());
			dlr.setDescription(json.get("description").toString());
			dlr.setGuid(json.get("guid").toString());
			dlr.setMsisdn(json.get("msisdn").toString());
			dlr.setNetwork(json.get("network").toString());
			dlr.setRef(json.get("ref").toString());
			dlr.setStsBillingReference(json.get("StsBillingReference").toString());
			dlr.setTag(json.get("tag").toString());
			dlr.setType(json.get("type").toString());
			if(type.equalsIgnoreCase("Redirect"))
			{
				dlr.setStatus("1");
			}
			else
			{
				dlr.setStatus("0");
			}
			
		//	CallBack{type=Navigation, date=2024-05-02 09:24:14 PM, action=Redirect, msisdn=27784164170, network=2, guid=5e71ed9c-760b-421d-8f04-d36d78118a3c, charge=0.00, ref=Ext_Ref, tag=Ext_Ref, StsBillingReference=, description=}
			dlrRepo.save(dlr);
			response.put("response", "Saved");
			if(type.equalsIgnoreCase("Redirect"))
			{
				
				Tbl_Player player = playerRepository.findByMsisdn(ani);
				if(player==null)
				{
					Tbl_Player tbl_Player = new Tbl_Player();
					tbl_Player.setMsisdn(ani);
					tbl_Player.setDatetime(LocalDateTime.now());
					tbl_Player.setPlayingdatetime(LocalDateTime.now());
					tbl_Player.setGameid("1");					
					playerRepository.save(tbl_Player);
					System.out.println("Save In table player to check who user play the game");
					
				}
				else
				{
					player.setPlayingdatetime(LocalDateTime.now());
					playerRepository.save(player);
					//udpate player playing date time 
				}
				
				TblSubscription checkUserNextDate = subRepo.findByMsisdn(ani);
				
				if(checkUserNextDate==null)
				{
					System.out.println("Not subscribed");
					response.put("result", "3");
				}
				else if(checkUserNextDate.getNext_billed_date()==null)
				{
					System.out.println("Billing Pending");

					response.put("result", "2");
				}
				
				else if(checkUserNextDate.getNext_billed_date().isAfter(LocalDateTime.now()))
				{
					System.out.println("User can play ");

					response.put("result", "1");
					Tbl_Player sendPlayerLink = playerRepository.findByMsisdn(ani);
					String gameurl = "https://www.gameninja.in/html/gv1/wingameNewMTNSA/index.html?userid="+sendPlayerLink.getId()+"&gameId=1";
					
					response.put("msisdn",gameurl);
				}
				else
				{
					System.out.println("inside else");

					response.put("result", "2");
				}
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("response "+response);
		return response;
	}
}


//https://www.gameninja.in/html/gv1/wingameT/index.html?uid=<userId>&gid=2
