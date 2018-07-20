package com.lph.excloudapiprice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("price")
public class PriceController {


    @GetMapping("say")
    public String say(@RequestParam String name){
        return "hello:"+name;
    }
}
