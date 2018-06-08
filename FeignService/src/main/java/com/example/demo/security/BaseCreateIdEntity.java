package com.example.demo.security;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * 所有主表的父类
 * 用于实现添加创建人功能
 */
@EntityListeners(value={JPACreateIdEventListener.class})
@MappedSuperclass
public class BaseCreateIdEntity {

    /**
     * 创建人
     * 所有主表都需要创建人id
     */
    @Column(name = "CREATE_ID")
    protected Long createId;

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }
}
