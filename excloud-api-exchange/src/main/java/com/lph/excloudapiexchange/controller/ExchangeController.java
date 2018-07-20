package com.lph.excloudapiexchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exchange")
public class ExchangeController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplateBuilder restTemplate;

    @GetMapping("sayhi")
    public String sayhi(String name){
        String uri= discoveryClient.getInstances("price").get(0).getUri().toString();
        String serviceId= discoveryClient.getInstances("price").get(0).getServiceId();
        String url = uri+"/"+serviceId.toLowerCase()+"/say?name="+name;
        return restTemplate.build().getForObject(url,String.class);

    }
}
