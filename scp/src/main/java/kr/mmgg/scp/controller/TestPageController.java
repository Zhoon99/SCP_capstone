package kr.mmgg.scp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestPageController {

    @GetMapping("/home")
    public String home() {
        return "TeamHome";
    }
}
