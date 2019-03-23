package com.lph.excloudapiexchange.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Configuration
//@EnableAutoConfiguration
@RestController
@RefreshScope
@RequestMapping("exchange")
public class ExchangeController {

    @Value("${name}")
    private String name;

//    @Autowired
//    IUserService userService;
//
//    @Autowired
//    TradeMapper tradeMapper;

//    @Autowired
//    private DiscoveryClient discoveryClient;
//    @Autowired
//    private RestTemplateBuilder restTemplate;
//
//    @GetMapping("sayhi")
//    public String sayhi(String name){
//        String uri= discoveryClient.getInstances("price").get(0).getUri().toString();
//        String serviceId= discoveryClient.getInstances("price").get(0).getServiceId();
//        String url = uri+"/"+serviceId.toLowerCase()+"/say?name="+name;
//        return restTemplate.build().getForObject(url,String.class);
//
//    }

//    @GetMapping("user")
//    public String getUserById(Long id){
//        User user = userService.getUserById(id);
//        return user.getUserName();
//    }

    @RequestMapping("/hi")
    public String hi(){
        return "Hello "+name;
    }
}
