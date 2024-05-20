package com.Controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Service.SendTermsService;

@RestController
public class SendTerms 
{
	@Autowired
	private SendTermsService service;
	
	@CrossOrigin
	@GetMapping("/sendTerms")
	public ResponseEntity<Map<String,String>> sendTerms(@RequestParam("gameId")String gameId)
	{
		Map<String,String>response=new HashMap<>();
		try
		{
			String terms = service.sendTermsService(gameId);
			response.put("response","1");
			response.put("terms",terms);
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			response.put("response","0");
			response.put("terms","");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
}