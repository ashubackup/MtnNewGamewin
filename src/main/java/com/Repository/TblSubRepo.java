package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblSubscription;

@Repository
public interface TblSubRepo extends JpaRepository<TblSubscription, Integer>
{

	TblSubscription findByMsisdn(String ani);
}
