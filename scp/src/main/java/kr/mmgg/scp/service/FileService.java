package kr.mmgg.scp.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import kr.mmgg.scp.dto.ResultDto;

public interface FileService {
    public ResultDto<?> fileUpload(List<File> fList, Long taskId);

    public ResultDto<?> fileDownload(HttpServletResponse response, Long fileId);
}
