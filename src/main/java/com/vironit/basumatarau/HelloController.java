package com.vironit.basumatarau;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(path = "/")
    public String hello(){
        return "hello (within ssl)...";
    }
}
