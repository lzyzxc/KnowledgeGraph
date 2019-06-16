package com.tongji.knowledgegraph.controller;

import com.tongji.knowledgegraph.service.MainService;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    MainService mainService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public StatementResult query(){
        return mainService.test();
    }

}
