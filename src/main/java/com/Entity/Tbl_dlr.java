package com.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_dlr")
public class Tbl_dlr 
{

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	private String type;
	private LocalDateTime date;
	private String action;
	private String msisdn;
	private String network;
	private String guid;
	private String charge;
	private String ref;
	private String tag;
	private String StsBillingReference;
	private String description;
	private String status;
}
