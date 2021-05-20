package com.xq.tmall.entity;

import java.io.Serializable;

/**
 * (Collect)实体类
 *
 * @author makejava
 * @since 2021-04-25 21:21:18
 */
public class Collect implements Serializable {
    private static final long serialVersionUID = 872549629540947469L;

    private Integer userId;

    private Integer productId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}
