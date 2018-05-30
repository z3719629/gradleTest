package com.example.demo.interfaces;

import com.example.demo.hihystric.SchedualServiceHiHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/5/28.
 */
@FeignClient(value = "eureka-discovery-second", fallback = SchedualServiceHiHystric.class)
public interface ComputeClientSecond {

    @RequestMapping(method = RequestMethod.GET, value = "/sub")
    Integer sub(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);

}
