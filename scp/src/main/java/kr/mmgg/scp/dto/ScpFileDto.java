package kr.mmgg.scp.dto;

import lombok.Data;

@Data
public class ScpFileDto {
    Long filesize;
    String fileName;
    String fileExtension;
}
