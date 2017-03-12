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
import java.util.List;


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
    public TJob addJob(TJob job) {
        return jobDao.save(job);
    }

    @Override
    public TJob updateJob(TJob job) {
        return jobDao.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        jobDao.delete(id);
    }

    @Override
    public List<TJob> getOnineList(int pageNum) {
        return this.getList(TJob.STATUS_ONLINE, pageNum);
    }

    @Override
    public List<TJob> getOfflineList(int pageNum) {
        return this.getList(TJob.STATUS_OFFLINE, pageNum);
    }

    private List<TJob> getList(byte status, int pageNum) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum, 20, sort);
        logger.debug("status:{} pagenum:{} pageoffset:{} pagesize:{} pagenum:{}",
                status,
                pageable.getPageNumber(),
                pageable.getOffset(),
                pageable.getPageSize(),
                pageable.getPageNumber());
        return jobDao.queryListByStatus(status, pageable);
    }
}
