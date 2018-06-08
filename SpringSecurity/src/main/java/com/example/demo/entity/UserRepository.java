package com.example.demo.entity;

import com.example.demo.entity.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<SysUsers, Long> {

    public List<SysUsers> findByUsername(String username);
}
