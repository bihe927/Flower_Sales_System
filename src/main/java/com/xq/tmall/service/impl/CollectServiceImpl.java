package com.xq.tmall.service.impl;

import com.xq.tmall.dao.CollectMapper;
import com.xq.tmall.entity.Collect;
import com.xq.tmall.service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Collect)表服务实现类
 *
 * @author makejava
 * @since 2021-04-25 22:20:02
 */
@Service("collectService")
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectMapper collectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public Collect queryById(Integer userId) {
        return this.collectDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Collect> queryAllByLimit(int offset, int limit) {
        return this.collectDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    @Override
    public Collect insert(Collect collect) {
        this.collectDao.insert(collect);
        return collect;
    }

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    @Override
    public Collect update(Collect collect) {
        this.collectDao.update(collect);
        return this.queryById(collect.getUserId());
    }

    /**
     *
     * @param productId
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer productId) {
        return this.collectDao.deleteById(productId) > 0;
    }

    @Override
    public List<Collect> queryAll(Collect collect) {
        return this.collectDao.queryAll(collect);
    }

}
