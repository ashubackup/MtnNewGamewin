package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Entity.TblAirtime;

@Repository

public interface AirtimeRepo extends JpaRepository<TblAirtime, Integer>
{

	@Query(value="SELECT * from tbl_airtime_data where msisdn=:msisdn and gameId=:gameId and date(datetime)=date(subdate(now(),1))",nativeQuery = true)
	public TblAirtime findByMsisdnAndGameId(@Param("msisdn") String msisdn,@Param("gameId") String gameId);
}
