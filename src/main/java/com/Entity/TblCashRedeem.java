package com.Entity;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
@Table(name="tbl_cash_redeem")
public class TblCashRedeem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ani;
	private String userid;
	private Integer coins;
	private LocalDateTime datetime;
	private String gameid;
	private String status;
	@Column(name="modifieddatetime")
	private LocalDateTime modifiedDatetime;
	private String checkStatus;
}
