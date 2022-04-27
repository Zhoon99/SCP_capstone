package kr.mmgg.scp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.ScpFileUploadDto;
import kr.mmgg.scp.service.FileService;
import kr.mmgg.scp.util.ScpFileUtils;

@RestController
public class FileHandlingController {
    @Autowired
    private FileService fileService;

    @PostMapping(value = "/fileupload")
    public ResultDto<?> testfileUpload(@RequestBody ScpFileUploadDto Dto) {
        return fileService.fileUpload(Dto);
    }

    @GetMapping("/test")
    public String test() {
        return "filetest";
    }
}
