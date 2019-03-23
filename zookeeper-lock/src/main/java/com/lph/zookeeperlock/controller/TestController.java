package com.lph.zookeeperlock.controller;

import com.lph.zookeeperlock.annotation.Lock;
import com.lph.zookeeperlock.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired private FileService fileService;

    @Lock
    @GetMapping("/curator")
    public Object curator(){
        fileService.addOne();
        return "done";
    }

}
