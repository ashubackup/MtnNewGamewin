package com.Repository;
import java.util.List;		
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Entity.TblRedeemNew;
import com.Modal.*;

@Repository
public interface TblRedeemNewRepo extends JpaRepository<TblRedeemNew, Integer>
{
	@Query(value="SELECT coins FROM tbl_redeem_new WHERE DATE(DATETIME)=DATE(SUBDATE(NOW(),0)) AND checkStatus='0' AND coins IS NOT NULL\r\n"
			+ "AND gameid=:game ORDER BY coins DESC LIMIT :lim",nativeQuery=true)
	public List<WinnersReponse> getWinners(@Param("game")String game,@Param("lim") Integer lim);
	
	@Query(value="SELECT coins FROM tbl_redeem_new WHERE DATE(DATETIME)=DATE(SUBDATE(NOW(),0)) AND checkStatus='0' AND coins IS NOT NULL\r\n"
			+ "AND gameid=:game AND ani=:ani ORDER BY coins DESC LIMIT 1\r\n"
			+ "",nativeQuery=true)
	public WinnersReponse getUserScore(@Param("game")String game,@Param("ani")String ani);
	
	@Query(value="SELECT * FROM tbl_redeem_new WHERE DATE(DATETIME)=DATE(SUBDATE(NOW(),0)) AND checkStatus='0' AND coins IS NOT NULL\r\n"
			+ "AND gameid=:game ORDER BY coins DESC LIMIT 1\r\n"
			+ "",nativeQuery=true)
	public WinnersReponse getBestScore(@Param("game")String game);
	
	//Not Using
	@Query(value="SELECT MAX(coins) AS coins FROM tbl_redeem_new WHERE gameid=:game\r\n"
			+ "AND coins IS NOT NULL AND DATE(modifieddatetime) = CURDATE()\r\n"
			+ "GROUP BY userid ORDER BY MAX(coins) DESC LIMIT :lim\r\n"
			+ "",nativeQuery=true)
	public List<WinnersReponse> getAirtimeWinnersOldWay(@Param("game")String game,@Param("lim") Integer lim);
	
	//Not Using
	@Query(value="SELECT MAX(coins) AS coins FROM tbl_redeem_new WHERE gameid=:game\r\n"
			+ "AND coins IS NOT NULL AND MONTH(modifieddatetime) =  MONTH(NOW())\r\n"
			+ "GROUP BY userid ORDER BY MAX(coins) DESC LIMIT :lim",nativeQuery=true)
	public List<WinnersReponse> getCashWinnersOldWay(@Param("game")String game,@Param("lim") Integer lim);
}