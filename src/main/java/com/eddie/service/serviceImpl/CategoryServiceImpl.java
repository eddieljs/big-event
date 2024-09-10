package com.eddie.service.serviceImpl;

import com.eddie.mapper.CategoryMapper;
import com.eddie.pojo.Category;
import com.eddie.service.CategoryService;
import com.eddie.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 新增分类
     * @param category
     */
    @Override
    public void add(Category category) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        categoryMapper.insert(category);
    }

    /**
     * 查询分类列表
     * @return
     */
    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<Category> list = categoryMapper.list(id);
        return list;
    }

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @Override
    public Category getById(Integer id) {
        Category category = categoryMapper.getById(id);
        return category;
    }

    /**
     * 更新文章分类
     * @param category
     */
    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    /**
     * 删除文章
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }


}
