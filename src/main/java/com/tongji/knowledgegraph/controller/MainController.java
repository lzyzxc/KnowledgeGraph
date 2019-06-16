package com.tongji.knowledgegraph.controller;

import com.tongji.knowledgegraph.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    MainService mainService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public ResponseEntity<?> query(@RequestParam String name){
        return mainService.test("Idemitsu Kosan Co Ltd");
    }

}
