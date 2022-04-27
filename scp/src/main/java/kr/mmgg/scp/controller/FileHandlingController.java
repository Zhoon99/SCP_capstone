package kr.mmgg.scp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.FileUploadDto;
import kr.mmgg.scp.util.MyFileUtils;

@RestController
public class FileHandlingController {

    @PostMapping(value = "/fileupload")
    public ResultDto<?> testfileUpload(@RequestBody FileUploadDto Dto) {
        return null;
    }

    @GetMapping("/test")
    public String test() {
        return "filetest";
    }
}
