package kr.mmgg.scp.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FIleUpload {
    public String fileUpload(MultipartHttpServletRequest request, String fileName)
            throws IllegalStateException, IOException {
        // 저장 공간
        String path = "C:\\Users\\Admin\\Desktop\\test";
        File file = new File(path);
        // 디렉토리 없으면 새로 만듬
        if (file.exists() == false) {
            file.mkdirs();
        }
        // from 태그 안에 input타입이 file인 친구들을 다 가져온다
        Iterator<String> FileIt = request.getFileNames();
        String name;
        String newFileName = null;
        while (FileIt.hasNext()) {
            name = FileIt.next();

            // input타입이 file인 친구들중 하나를 꺼내 List에 넣는다.
            List<MultipartFile> list = request.getFiles(name);
            for (MultipartFile multipartFile : list) {
                // // 파일 dto 생성
                // FileDto fileDto = new FileDto();

                newFileName = fileName + multipartFile.getOriginalFilename()
                        .substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
                file = new File(path + "/" + newFileName);
                multipartFile.transferTo(file);
            }

        }
        return newFileName;
    }
}
