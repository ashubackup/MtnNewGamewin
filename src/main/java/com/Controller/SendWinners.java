package com.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Modal.WinnersRequest;
import com.Service.SendWinnersService;

@RestController
public class SendWinners 
{
	@Autowired
	private SendWinnersService service;
	
	@CrossOrigin
	@PostMapping("/sendWinners")
	public ResponseEntity<?> sendWinners(@RequestBody WinnersRequest request)
	{
		Map<String, List<String>> response=new HashMap<String, List<String>>();
		try
		{
			List<String> daily = service.sendAirtimeWinners(request);
			List<String> monthly = service.sendCashWinners(request);
			
			response.put("daily", daily);
			response.put("monthly",monthly);
			
			return ResponseEntity.ok(response);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(500).body(response);
		}
	}
}
