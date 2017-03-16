package com.cew.service;

import com.cew.entity.TJob;

import java.util.List;

/**
 * Created by chenchaofei on 2017/3/10.
 */
public interface JobService {
    TJob findById(Long id);

    boolean existsById(Long id);

    Long add(TJob job);

    boolean modify(TJob job);

    void delete(Long id);

    void changeStatus(Long id, byte status);

    List getOnlineListByFullField(int pageNum);

    List getOnlineList(int pageNum);

    Integer getOnlineCount();

    List getOfflineList(int pageNum);

    Integer getOfflineCount();
}
