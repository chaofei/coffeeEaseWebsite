package com.cew.service.impl;

/**
 * Created by chenchaofei on 2017/3/10.
 */

import com.cew.dao.TJobDao;
import com.cew.entity.TJob;
import com.cew.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class JobServiceImpl implements JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    private TJobDao jobDao;


    @Override
    public TJob findById(Long id) {
        return jobDao.findOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jobDao.exists(id);
    }

    @Override
    public TJob add(TJob job) {
        return jobDao.save(job);
    }

    @Override
    public void modify(TJob job) {
        jobDao.modify(job.getId(), job.getTitle());
    }

    @Override
    public void delete(Long id) {
        jobDao.delete(id);
    }

    @Override
    public void changeStatus(Long id, byte status) {
        if(status == TJob.STATUS_ONLINE || status == TJob.STATUS_OFFLINE) {
            jobDao.updateStatus(id, status);
        }
    }

    @Override
    public List getOnlineList(int pageNum) {
        return this.getList(TJob.STATUS_ONLINE, pageNum);
    }

    @Override
    public List getOfflineList(int pageNum) {
        return this.getList(TJob.STATUS_OFFLINE, pageNum);
    }

    private List getList(byte status, int pageNum) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum, 10, sort);
        logger.debug("status:{} pagenum:{} pageoffset:{} pagesize:{}",
                status,
                pageable.getPageNumber(),
                pageable.getOffset(),
                pageable.getPageSize());
        List<TJob> list = jobDao.queryListByStatus(status, pageable);

        List ret = new ArrayList();
        for(int i=0; i<list.size(); i++) {
            TJob dbJob = list.get(i);
            Map job = new HashMap();
            job.put("id", dbJob.getId());
            job.put("title", dbJob.getTitle());
            ret.add(job);
        }
        return ret;
    }

    @Override
    public Integer getOnlineCount() {
        return jobDao.queryCountByStatus(TJob.STATUS_ONLINE);
    }

    @Override
    public Integer getOfflineCount() {
        return jobDao.queryCountByStatus(TJob.STATUS_OFFLINE);
    }
}
