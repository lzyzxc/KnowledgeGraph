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
        return mainService.matchOrg(name);
    }

    @RequestMapping(value = "/queryPerson",method = RequestMethod.GET)
    public ResponseEntity<?> queryPerson(@RequestParam String familyName,@RequestParam String givenName){
        return mainService.matchPersonOrg(familyName, givenName);
    }

    @RequestMapping(value = "/multiHop",method = RequestMethod.GET)
    public ResponseEntity<?> multiHop(@RequestParam String org1,@RequestParam String org2,@RequestParam Integer step){
        return mainService.multiHop(org1,org2,step);
    }

    @RequestMapping(value = "/around",method = RequestMethod.GET)
    public ResponseEntity<?> around(@RequestParam String label,@RequestParam Integer id){
        return mainService.around(label,id);
    }
}
