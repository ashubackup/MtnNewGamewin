	package com.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Service.CallBackService;

@RestController
@CrossOrigin("*")
public class CalBackController 
{
	
	@Autowired
	CallBackService callBackService;

	@GetMapping("/callback")
	public Map<String,String> getCallBack(@RequestParam Map<String, String> params)
	{
		Map<String,String> response = new HashMap<String, String>();
		try
		{
			System.out.println("Value of params "+params);
			response = callBackService.callBack(params);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}

}
