package com.cew.service;

import com.cew.entity.TJob;

import java.util.List;

/**
 * Created by chenchaofei on 2017/3/10.
 */
public interface JobService {
    TJob findById(Long id);

    TJob addJob(TJob job);

    TJob updateJob(TJob job);

    void deleteJob(Long id);

    List<TJob> getOnineList(int pageNum);

    List<TJob> getOfflineList(int pageNum);
}
