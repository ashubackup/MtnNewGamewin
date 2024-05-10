package com.Controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Modal.SubRequest;
import com.Service.CheckSubService;

@RestController
public class CheckSubscription 
{
	@Autowired
	private CheckSubService service;
	
	@CrossOrigin
	@PostMapping("/checkSubscription")
	public ResponseEntity<?> checkSubscription(@RequestBody SubRequest request)
	{
		Map<String, String>response=new HashMap<String, String>();
		try
		{
			if(request.getGameId().equalsIgnoreCase("1000"))
			{
				response.put("response", "1");
			}
			else
			{
				String result = service.checkSub(request);
				response.put("response", result);
			}
			
			return ResponseEntity.ok(response);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(200).body(response);
		}
	}
}
