package com.cew.dao;

/**
 * Created by chenchaofei on 2017/3/10.
 */

import com.cew.entity.TJob;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TResumeDao extends PagingAndSortingRepository<TJob, Long>, JpaSpecificationExecutor<TJob> {


}
