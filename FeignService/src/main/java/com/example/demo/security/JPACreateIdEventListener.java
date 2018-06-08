package com.example.demo.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;

/**
 * spring data jpa 事件监听器
 * 用于监听所有主表的数据插入操作
 */
@Component("jpaCreateIdEventListener")
public class JPACreateIdEventListener {

    // 新增时调用
    @PrePersist
    public void prePersist(BaseCreateIdEntity baseCreateIdEntity) {
        System.out.println("prePersist called");
        // 取出登录用户
        SysUsers sysUsers = (SysUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        baseCreateIdEntity.setCreateId(sysUsers.getId());
    }
}
