package com.example.demo.hihystric;

import com.example.demo.interfaces.ComputeClient;
import com.example.demo.interfaces.ComputeClientSecond;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/5/29.
 */
@Component
public class SchedualServiceHiHystric implements ComputeClient, ComputeClientSecond {

    @Override
    public Integer add(Integer a, Integer b) {
        return -1;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return -2;
    }
}
