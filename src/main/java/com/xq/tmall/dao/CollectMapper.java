package com.xq.tmall.dao;

import com.xq.tmall.entity.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Collect)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-25 22:18:25
 */
public interface CollectMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    Collect queryById(Integer userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Collect> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param collect 实例对象
     * @return 对象列表
     */
    List<Collect> queryAll(Collect collect);

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 影响行数
     */
    int insert(Collect collect);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Collect> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Collect> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Collect> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Collect> entities);

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 影响行数
     */
    int update(Collect collect);

    /**
     *
     * @param productId
     * @return 影响行数
     */
    int deleteById(Integer productId);

}

