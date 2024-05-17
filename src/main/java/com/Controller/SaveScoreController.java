package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Modal.SaveScoreRequest;
import com.Service.SaveScoreService;

@RestController
@CrossOrigin("*")
public class SaveScoreController 
{
	
	@Autowired
	SaveScoreService saveScoreService;

	@PostMapping("/savescore")
	public String saveScore(@RequestBody SaveScoreRequest body)
	{
		String response = "Failed";
		try
		{
			System.out.println("SaveScore Request "+body);
			response=saveScoreService.scoreService(body);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
