package com.framework.pie.admin.service.impl;

import com.framework.pie.admin.model.SysArticle;
import com.framework.pie.admin.dao.SysArticleMapper;
import com.framework.pie.admin.service.SysArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.framework.pie.core.page.MybatisPageHelper;
import com.framework.pie.core.page.PageRequest;
import com.framework.pie.core.page.PageResult;
import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 文章  服务实现类
 * </p>
 *
 * @author longlong
 * @since 2020-09-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysArticleServiceImpl implements SysArticleService {
    @Resource
    private SysArticleMapper sysArticleMapper;

    @Override
    public int save(SysArticle record) {
      if (record.getId() == null || record.getId() == 0){
        return sysArticleMapper.insertSelective(record);
       }
       return sysArticleMapper.updateByPrimaryKeySelective(record);
    }
   @Override
    public int delete(SysArticle record) {
       return sysArticleMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysArticle> records) {
        for (SysArticle record : records){
            delete(record);
        }
        return 1;
    }

    @Override
    public SysArticle findById(Long id) {
        return sysArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        return MybatisPageHelper.findPage(pageRequest,sysArticleMapper);
    }

    @Override
    public SysArticle saveArticle(SysArticle record) {
        if (record.getId() == null || record.getId() == 0){
            sysArticleMapper.insertSelective(record);
            return record;
        }
        sysArticleMapper.updateByPrimaryKeySelective(record);
        return record;
    }
}
