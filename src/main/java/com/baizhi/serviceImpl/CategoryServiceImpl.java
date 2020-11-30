package com.baizhi.serviceImpl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import com.baizhi.po.CategoryPO;
import com.baizhi.service.CategoryService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @AddLog("(category)(后台)查询总条数（一级）(二级）")
    //后台：查询总条数（一级）(二级）
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public long findTotalCount(String grade) {
        Category category = new Category();
        category.setGrade(grade);
        return categoryMapper.selectCount(category);
    }

    @AddLog("(category)(后台)分页查询所有(一级)")
    //后台：分页查询所有(一级)
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findAllfy(Integer page, Integer rows,String grade) {
        int start = (page-1)*rows;
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andGradeEqualTo(grade);
        RowBounds rowBounds = new RowBounds(start,rows);
        return categoryMapper.selectByExampleAndRowBounds(categoryExample,rowBounds);
    }

    @AddLog("(category)(后台)分页查询所有(二级）")
    //后台：分页查询所有(二级）
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findAllfys(Integer page, Integer rows,String id) {
        int start = (page-1)*rows;
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andParentclassEqualTo(id);
        RowBounds rowBounds = new RowBounds(start,rows);
        return categoryMapper.selectByExampleAndRowBounds(categoryExample,rowBounds);
    }

    @DelCahe
    @AddLog("(category)(后台)增(一级)(二级）")
    //后台：增(一级)(二级）
    @Override
    public void save(Category category) {
        try{
            if(category.getParentclass()==null){
                category.setId(UUID.randomUUID().toString());
                category.setGrade("1");
            }else {
                category.setId(UUID.randomUUID().toString());
                category.setGrade("2");
            }
            categoryMapper.insertSelective(category);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("添加类别失败");
        }
    }

    @DelCahe
    @AddLog("(category)(后台)删(一级)")
    //后台：删(一级)
    @Override
    public void delete(Category category) {
        Category categoryss = new Category();
        categoryss.setParentclass(category.getId());
        List<Category> categorys = categoryMapper.select(categoryss);
        try {
            if(categorys.size()==0) {
                categoryMapper.delete(category);
            } else {
                throw new RuntimeException("删除失败,该一级类别下有二级类别");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DelCahe
    @AddLog("(category)(后台)删(二级）")
    //后台：删(二级）
    @Override
    public void deletes(Category category) {
        try{
            categoryMapper.delete(category);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除类别失败");
        }
    }

    @DelCahe
    @AddLog("(category)(后台)改（一级）(二级）")
    //后台：改（一级）(二级）
    @Override
    public void update(Category category) {
        try{
            categoryMapper.updateByPrimaryKeySelective(category);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("修改类别失败");
        }
    }


    //前台查询
    @Override
    public List<CategoryPO> findAllms(){
        return categoryMapper.findAllms();
    }


}
