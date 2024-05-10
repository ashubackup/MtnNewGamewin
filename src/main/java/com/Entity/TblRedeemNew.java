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
@Table(name="tbl_redeem_new")
public class TblRedeemNew 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="_id")
	private Integer id;
	private String ani;
	private String userid;
	private Integer coins;
	private LocalDateTime datetime;
	private String gameid;
	private String status;
	private LocalDateTime modifieddatetime;
	private String checkStatus;
}
