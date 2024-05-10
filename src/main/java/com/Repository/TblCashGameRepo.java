package com.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.TbCashGame;

@Repository
public interface TblCashGameRepo extends JpaRepository<TbCashGame,String>
{
	public TbCashGame findByGameid(String gameId);
}