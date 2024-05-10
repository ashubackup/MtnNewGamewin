package com.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Entity.TblSaveScore;
import com.Entity.Tbl_Player;
import com.Modal.SaveScoreRequest;
import com.Repository.PlayerRepository;
import com.Repository.TblScoreRepo;

@Component
public class SaveScoreService 
{
	@Autowired
	TblScoreRepo scoreRepo;
	
	@Autowired
	PlayerRepository playerRepository;

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
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
