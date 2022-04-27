package kr.mmgg.scp.dto.response;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.Data;

@Data
public class ScpFileUploadDto {
    MultipartHttpServletRequest file;
    String filePath;
    Long taskId;
}
