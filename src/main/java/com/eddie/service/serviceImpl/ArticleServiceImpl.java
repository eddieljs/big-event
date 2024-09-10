package com.eddie.service.serviceImpl;

import com.eddie.mapper.ArticleMapper;
import com.eddie.pojo.Article;
import com.eddie.pojo.PageBean;
import com.eddie.service.ArticleService;
import com.eddie.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 新增文章
     * @param article
     */
    @Override
    public void add(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.insert(article);
    }

    /**
     * 文章分页查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @Override
    public PageBean pageQuery(Integer pageNum, Integer pageSize, String categoryId, String state) {
        PageHelper.startPage(pageNum,pageSize);

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Page<Article> pageResult = articleMapper.pageQuery(categoryId,state,userId);//Page

        long total = pageResult.getTotal();
        List<Article> result = pageResult.getResult();
        return new PageBean(total,result);

    }
}
