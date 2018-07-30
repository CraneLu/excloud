package com.lph.excloudapiexchange.controller;

import com.lph.excloudapiexchange.entity.Trade;
import com.lph.excloudapiexchange.entity.User;
import com.lph.excloudapiexchange.mapper.TradeMapper;
import com.lph.excloudapiexchange.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("exchange")
public class ExchangeController {

    @Autowired
    IUserService userService;

    @Autowired
    TradeMapper tradeMapper;

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

    @GetMapping("user")
    public String getUserById(Long id){
        User user = userService.getUserById(id);
        return user.getUserName();
    }
}
