package com.ylw.springcloud.service.a;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

    @RequestMapping("/getMember")
    public String getMember() {
        return "this is getMember";
    }
}
