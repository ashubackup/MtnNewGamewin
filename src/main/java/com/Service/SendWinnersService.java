package com.Service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.GameWinners;
import com.Modal.WinnersReponse;
import com.Modal.WinnersRequest;
import com.Repository.GameWinnersRepo;
import com.Repository.TblCashRedeemRepo;
import com.Repository.TblRedeemNewRepo;

@Service
public class SendWinnersService 
{
	@Autowired
	private GameWinnersRepo winnersInfoRepo;
	
	@Autowired
	private TblRedeemNewRepo airtimeRepo;
	
	@Autowired
	private TblCashRedeemRepo cashRepo;
	
	public List<String> sendAirtimeWinners(WinnersRequest request) //This Method will return airtime winners
	{
		List<String> list=new ArrayList<String>();

		try
		{
			System.out.println("Request is "+request.getGameId());
			GameWinners info = winnersInfoRepo.findByGameAndStatus(request.getGameId(),"1");			
			System.out.println("Game is "+info.getGameName() +"& No. of winners are "+info.getWinners());
			
			List<WinnersReponse> winners = airtimeRepo.getWinners(request.getGameId(), Integer.parseInt(info.getWinners()));
			for(WinnersReponse win:winners)
			{
				list.add(win.getCoins());
			}
			
			System.out.println("Airtime Winners list is "+list);
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return list;
		}
	}
	
	public List<String> sendCashWinners(WinnersRequest request) //This Method will return cash winners
	{
		List<String> list=new ArrayList<String>();

		try
		{
			System.out.println("Request is "+request.getGameId());
			GameWinners info = winnersInfoRepo.findByGameAndStatus(request.getGameId(),"1");			
			System.out.println("Game is "+info.getGameName() +"& No. of winners are "+info.getWinners());

			List<WinnersReponse> winners = cashRepo.getWinners(request.getGameId(),Integer.parseInt(info.getWinners()));
			for(WinnersReponse win:winners)
			{
				list.add(win.getCoins());
			}
			
			System.out.println("Cash Winners list is "+list);
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return list;
		}
	}
}
