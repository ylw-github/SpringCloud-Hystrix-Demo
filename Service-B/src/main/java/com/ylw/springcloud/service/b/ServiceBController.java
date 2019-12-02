package com.ylw.springcloud.service.b;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceBController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getOrder")
    public String getOrder() {
        // order 使用rpc 远程调用技术 调用 会员服务
        String memberUrl = "http://app-service-a/getMember";
        String result = restTemplate.getForObject(memberUrl, String.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("会员服务调用订单服务,result:" + result);
        return result;
    }

    @HystrixCommand(fallbackMethod = "orderToUserInfoFallback")
    @RequestMapping("/getUserInfoHystrix")
    public String orderToUserInfoHystrix() {
        System.out.println("orderToUserInfo:" + "当前线程池名称:" + Thread.currentThread().getName());
        return getOrder();
    }

    @RequestMapping("/orderToUserInfoFallback")
    public String orderToUserInfoFallback() {
        return "系统错误!!!!";
    }
}
