package com.Repository;
import java.util.List;	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Entity.TblCashRedeem;
import com.Modal.WinnersReponse;

@Repository
public interface TblCashRedeemRepo extends JpaRepository<TblCashRedeem, Integer>
{
	@Query(value="SELECT coins FROM tbl_cash_redeem WHERE MONTH(DATETIME)=MONTH(CURDATE()) AND YEAR(DATETIME)=YEAR(CURDATE()) AND gameid=:game \r\n"
			+ "ORDER BY coins DESC LIMIT :lim",nativeQuery=true)
	public List<WinnersReponse> getWinners(@Param("game")String game,@Param("lim") Integer lim);
	
	@Query(value="Select * from tbl_cash_redeem where MONTH(DATETIME)=MONTH(CURDATE()) AND YEAR(DATETIME)=YEAR(CURDATE()) and ani=:ani and gameId=:gameId",nativeQuery = true)
	public TblCashRedeem findByAniAndGameId(@Param("ani") String ani,String gameId);
}
