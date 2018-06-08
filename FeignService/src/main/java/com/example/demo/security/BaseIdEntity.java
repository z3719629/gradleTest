package com.example.demo.security;

import javax.persistence.*;

/**
 * 用户存储id字段
 */
@MappedSuperclass
public class BaseIdEntity extends BaseCreateIdEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
