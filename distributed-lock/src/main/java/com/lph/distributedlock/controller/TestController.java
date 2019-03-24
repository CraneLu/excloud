package com.lph.distributedlock.controller;

import com.lph.distributedlock.annotation.DistributedLock;
import com.lph.distributedlock.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired private FileService fileService;

    @DistributedLock(key = "curator")
    @GetMapping("/curator")
    public Object curator(){
        fileService.addOne();
        return "done";
    }

}
