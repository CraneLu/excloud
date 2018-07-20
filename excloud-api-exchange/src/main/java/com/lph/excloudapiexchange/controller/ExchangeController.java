package com.lph.excloudapiexchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("exchange")
public class ExchangeController {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("price")
    public String getPrice(String token){
        String path= discoveryClient.getInstances("price").get(0).getUri().getPath();
        String serviceId= discoveryClient.getInstances("price").get(0).getServiceId();
        return restTemplate.getForObject("http://"+serviceId+"/price/token?token=ARP",String.class);
    }
}
