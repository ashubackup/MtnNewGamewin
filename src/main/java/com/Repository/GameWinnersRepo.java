package com.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.GameWinners;

@Repository
public interface GameWinnersRepo extends JpaRepository<GameWinners, Integer>
{
	public GameWinners findByGameAndStatus(String game, String status);
}
