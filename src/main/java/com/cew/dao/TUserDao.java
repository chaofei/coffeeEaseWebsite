package com.cew.dao;

/**
 * Created by chenchaofei on 2017/3/10.
 */

import com.cew.entity.TUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TUserDao extends PagingAndSortingRepository<TUser, Long>, JpaSpecificationExecutor<TUser> {

    TUser findByUserName(String userName);

//    @Query("from TUser t where id = :id")
//    List<TUser> queryFamilyList(@Param("id") Long id, Pageable pageable);

}
