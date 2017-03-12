package com.cew.dao;

/**
 * Created by chenchaofei on 2017/3/10.
 */

import com.cew.entity.TJob;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TJobDao extends PagingAndSortingRepository<TJob, Long>, JpaSpecificationExecutor<TJob> {

    @Query("from TJob t where status = :status")
    List<TJob> queryListByStatus(@Param("status") byte status, Pageable pageable);

}
