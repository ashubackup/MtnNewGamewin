package com.Modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveScoreRequest 
{
	
	private String userid;
	private String score;
//	private String gameId;
//	private LocalDateTime datetime;
}
