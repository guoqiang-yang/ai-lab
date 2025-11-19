package com.oscar.ailab.server.service.impl;

import com.oscar.ailab.server.dao.mapper.TesterMapper;
import com.oscar.ailab.server.dao.po.TesterPO;
import com.oscar.ailab.server.service.DbTesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class DbTesterServiceImpl implements DbTesterService {

    @Resource
    private TesterMapper testerMapper;

    public void get(Integer id) {
        TesterPO testerPO = testerMapper.selectById(id);
        log.info("Ret: {}", testerPO);
    }
}
