package com.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_cash_game")
public class TbCashGame 
{
	@Id
	private String gameid;
	private String gamename;
	private String operator;
}