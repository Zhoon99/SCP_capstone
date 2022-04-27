package kr.mmgg.scp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.mmgg.scp.dto.ScpFileDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.ScpFileUploadDto;
import kr.mmgg.scp.entity.ScpFile;
import kr.mmgg.scp.repository.ScpFileRepository;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.ScpFileUtils;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private ScpFileRepository fileRepository;

    private ScpFileUtils myFileUtils = new ScpFileUtils();

    // 파일 업로드
    @Override
    public ResultDto<?> fileUpload(ScpFileUploadDto fileinfoDto) {
        try {
            ScpFile scpFile = new ScpFile();
            List<ScpFileDto> fileDtos = myFileUtils.fileUpload(fileinfoDto.getFile(), fileinfoDto.getFilePath());
            for (ScpFileDto fileDto : fileDtos) {
                scpFile.setFileName(fileDto.getFileName());
                scpFile.setFileExtension(fileDto.getFileExtension());
                scpFile.setFilePath(fileinfoDto.getFilePath());
                scpFile.setFileSize(fileDto.getFilesize());
                scpFile.setTaskId(fileinfoDto.getTaskId());
                fileRepository.save(scpFile);
            }
        } catch (IllegalStateException | IOException e) {
            throw new CustomException(ErrorCode.FILE_ERROR);
        }
        return new ResultDto<>().makeResult(CustomStatusCode.CREATE_SUCCESS);
    }
}
