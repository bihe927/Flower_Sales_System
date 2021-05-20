package com.xq.tmall.service;

import com.xq.tmall.entity.Collect;

import java.util.List;

/**
 * (Collect)表服务接口
 *
 * @author makejava
 * @since 2021-04-25 22:20:01
 */
public interface CollectService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    Collect queryById(Integer userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Collect> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    Collect insert(Collect collect);

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    Collect update(Collect collect);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer productId);

    List<Collect> queryAll(Collect collect);

}
