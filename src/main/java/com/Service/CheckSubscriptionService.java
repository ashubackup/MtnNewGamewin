package com.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblSubscription;
import com.Modal.CheckSubscriptionModal;
import com.Repository.TblSubRepo;

@Service
public class CheckSubscriptionService 
{
	
	@Autowired
	TblSubRepo subRepo;

	public Map<String,String> checkSubService(CheckSubscriptionModal checkSubscriptionModal)
	{
		Map<String,String> response = new HashMap<String,String>();
		try
		{
			TblSubscription subscription = subRepo.findByMsisdn(checkSubscriptionModal.getUserId());
			
			if(subscription==null)
			{
				response.put("response", "You are not subscriber");
			}
			
			else if(subscription.getNext_billed_date()==null)
			{
				response.put("response", "Billing Failed");
			}else if(subscription.getNext_billed_date().isAfter(LocalDateTime.now()))
			{
				response.put("response", "Subscribed");
			}
			else
			{
				response.put("response", "You are not subscriber");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.put("response", "You are not subscriber");
		}
		return response;
	}
}
