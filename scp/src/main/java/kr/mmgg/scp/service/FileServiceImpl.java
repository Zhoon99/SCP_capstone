package kr.mmgg.scp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.FileUploadDto;
import kr.mmgg.scp.repository.FileRepository;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.MyFileUtils;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    private MyFileUtils myFileUtils = new MyFileUtils();

    public ResultDto<?> fileUpload(FileUploadDto fileinfoDto) {
        try {
            myFileUtils.fileUpload(fileinfoDto.getFile(), fileinfoDto.getFilePath());

        } catch (IllegalStateException | IOException e) {
            throw new CustomException(ErrorCode.FILE_ERROR);
        }
        return new ResultDto<>().makeResult(CustomStatusCode.CREATE_SUCCESS);
    }
}
