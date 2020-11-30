package com.baizhi.serviceImpl;

import com.baizhi.dao.FeedbackMapper;
import com.baizhi.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Resource
    FeedbackMapper feedbackMapper;
}
