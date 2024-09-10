package com.eddie.controller;

import com.eddie.pojo.Category;
import com.eddie.pojo.Result;
import com.eddie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        log.info("新增分类，{}",category.getCategoryName());
        return Result.success("新增分类成功");
    }

    /**
     * 查询分类列表
     * @return
     */
    @GetMapping
    public Result<List<Category>> list(){
        log.info("查询分类列表");
        List<Category> list = categoryService.list();
        return Result.success(list);
    }

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result<Category> getById(Integer id){
        log.info("获取文章详情,{}",id);
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    /**
     * 更新文章分类
     * @param category
     * @return
     */
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        log.info("更新文章分类：{}",category.getCategoryName());
        categoryService.update(category);
        return Result.success("更新分类成功");
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }
}
