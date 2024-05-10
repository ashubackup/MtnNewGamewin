package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Tbl_Player;

@Repository
public interface PlayerRepository extends JpaRepository<Tbl_Player, Integer>
{

	Tbl_Player findByMsisdn(String msisdn);
	Tbl_Player findByIdAndGameid(Integer id,String gameId);
}
