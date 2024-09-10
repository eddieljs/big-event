package com.eddie.service.serviceImpl;

import com.eddie.mapper.ArticleMapper;
import com.eddie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

}
