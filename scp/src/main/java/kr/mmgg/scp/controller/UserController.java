package kr.mmgg.scp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/usertest")
    public void userTest(){
        
    }
}
