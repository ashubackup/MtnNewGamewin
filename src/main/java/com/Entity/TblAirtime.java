package com.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_airtime_data")
public class TblAirtime 
{

	private Integer id;
	private String msisdn;
	private String gameId;
	private String score;
	@Column(columnDefinition = "TEXT")
	private String request;
	
	@Column(columnDefinition = "TEXT")
	private String response;
	
	private String status;
	private LocalDateTime datetime;
}
