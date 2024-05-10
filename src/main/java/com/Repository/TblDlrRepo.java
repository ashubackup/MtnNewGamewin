package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Tbl_dlr;

@Repository
public interface TblDlrRepo extends JpaRepository<Tbl_dlr, Integer>
{

	
}
