package kr.mmgg.scp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.service.FileService;
import kr.mmgg.scp.util.ScpFileUtils;

@RestController
public class FileHandlingController {
    @Autowired
    private FileService fileService;

    @Autowired
    private ScpFileUtils scpFileUtils;

    @PostMapping(value = "/fileupload/{projectId}/{taskId}")
    public ResultDto<?> fileUpload(MultipartHttpServletRequest request, @PathVariable Long projectId,
            @PathVariable Long taskId) throws IllegalStateException, IOException {
        List<File> fileList = scpFileUtils.fileUpload(request,
                System.getProperty("user.dir") + "/scp/src/main/resources/static/files/" + projectId + "/" + taskId);
        return fileService.fileUpload(fileList, taskId);
    }

    @GetMapping(value = "/filedownload/{fileId}")
    public ResultDto<?> fileDownload(HttpServletResponse response, @PathVariable Long fileId) {
        return fileService.fileDownload(response, fileId);
    }

}
