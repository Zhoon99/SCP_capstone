package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.ScpFileUploadDto;

public interface FileService {
    public ResultDto<?> fileUpload(ScpFileUploadDto fileinfoDto);
}
