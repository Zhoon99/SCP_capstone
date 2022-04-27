package kr.mmgg.scp.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.mmgg.scp.util.MyFileUtils;

@RestController
public class TestFileUploadController {

    @PostMapping(value = "/testfile")
    public String testfileUpload(MultipartHttpServletRequest file)
            throws IllegalStateException, IOException {
        MyFileUtils fIleUpload = new MyFileUtils();
        fIleUpload.fileUpload(file, "test");
        return "redirect:filetest";
    }

    @GetMapping("/test")
    public String test() {
        return "filetest";
    }
}
