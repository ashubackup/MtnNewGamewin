package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblSaveScore;

@Repository
public interface TblScoreRepo extends JpaRepository<TblSaveScore, Integer>
{

}
