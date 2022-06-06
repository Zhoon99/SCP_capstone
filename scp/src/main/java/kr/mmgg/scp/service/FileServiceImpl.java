package kr.mmgg.scp.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.ResultDto;
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

    @Autowired
    private ScpFileUtils scpFileUtils;

    // 파일 업로드
    @Override
    @Transactional
    public ResultDto<?> fileUpload(List<File> fList, Long taskId) {
        List<ScpFile> fileEntityList = new ArrayList<>();
        for (File f : fList) {
            ScpFile fileEntity = new ScpFile();
            fileEntity.setFilePath(f.getAbsolutePath());
            fileEntity.setFileSize(f.length());
            fileEntity.setFileName(f.getName().substring(0, f.getName().indexOf(".")));
            fileEntity.setFileExtension(f.getName().substring(f.getName().lastIndexOf(".")));
            fileEntity.setTaskId(taskId);
            fileEntityList.add(fileEntity);
        }
        fileRepository.saveAll(fileEntityList);
        return new ResultDto<>().makeResult(CustomStatusCode.CREATE_SUCCESS);
    }

    @Override
    public ResultDto<?> fileDownload(HttpServletResponse response, Long fileId) {
        ScpFile fScpFile = fileRepository.findByFileId(fileId);
        try {
            System.out.println("filename: " + fScpFile.getFileName());
            scpFileUtils.fileDownload(response, fScpFile.getFilePath(),
                    fScpFile.getFileName() + fScpFile.getFileExtension());
        } catch (IOException e) {
            throw new CustomException(ErrorCode.FILE_ERROR);
        }
        return new ResultDto<>().makeResult(CustomStatusCode.LOOKUP_SUCCESS);
    }
}
