package com.cew.dao;

/**
 * Created by chenchaofei on 2017/3/10.
 */

import com.cew.entity.TJob;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TJobDao extends PagingAndSortingRepository<TJob, Long>, JpaSpecificationExecutor<TJob> {

    @Query("select t from TJob t where status = :status")
    List<TJob> queryListByStatus(@Param("status") byte status, Pageable pageable);

    @Query(value = "select count(t) from TJob t where status = :status")
    Integer queryCountByStatus(@Param("status") byte status);

    @Modifying
    @Query("update TJob set title=:title," +
            "salary=:salary," +
            "addr=:addr," +
            "experience=:experience," +
            "welfare=:welfare," +
            "description=:description," +
            "requirements=:requirements," +
            "updateAt=:dt " +
            "where id=:id")
    void modify(@Param("id") Long id,
                @Param("title") String title,
                @Param("salary") String salary,
                @Param("addr") String addr,
                @Param("experience") String experience,
                @Param("welfare") String welfare,
                @Param("description") String description,
                @Param("requirements") String requirements,
                @Param("dt")Date dt);

    @Modifying
    @Query("update TJob set status=:status where id=:id")
    void updateStatus(@Param("id") Long id, @Param("status") byte status);

}
