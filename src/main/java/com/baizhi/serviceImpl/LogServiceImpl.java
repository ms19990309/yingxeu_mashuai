package com.baizhi.serviceImpl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Log;
import com.baizhi.entity.LogExample;
import com.baizhi.service.LogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {

    @Resource
    LogMapper logMapper;

    @DelCahe
    //后台：删
    @AddLog("(log)(后台)删")
    @Override
    public void delete(Log log) {
        logMapper.delete(log);
    }

    @AddLog("(log)(后台)查询总条数")
    //后台：查询总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public long findTotalCount() {
        Log log = new Log();
        return logMapper.selectCount(log);
    }

    @AddLog("(log)(后台)分页查询所有")
    //后台：分页查询所有
    @Override
    public List<Log> findAll(Integer page, Integer rows) {
        int start = (page-1)*rows;
        LogExample logExample = new LogExample();
        RowBounds rowBounds = new RowBounds(start,rows);
        return logMapper.selectByExampleAndRowBounds(logExample,rowBounds);
    }
}
