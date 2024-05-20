package com.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.TblGamesData;

@Repository
public interface TblGamesDataRepo extends JpaRepository<TblGamesData,Integer>
{
	public TblGamesData findByStatusAndGameId(String status,Integer gameId);
}