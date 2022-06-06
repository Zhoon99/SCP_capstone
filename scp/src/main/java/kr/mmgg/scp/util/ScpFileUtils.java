package kr.mmgg.scp.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class ScpFileUtils {
    public List<File> fileUpload(MultipartHttpServletRequest request, String filePath)
            throws IllegalStateException, IOException {
        List<File> fileList = new ArrayList<>();
        // 저장 공간
        String path = filePath;
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
                newFileName = multipartFile.getOriginalFilename();
                file = new File(path + "/" + newFileName);
                fileList.add(file);
                multipartFile.transferTo(file);

            }
        }
        return fileList;
    }

    public void fileDownload(HttpServletResponse response, String Filepath, String fileName) throws IOException {
        // 가져온 파일을 바이트로 변환한다.
        byte[] fileByte = FileUtils.readFileToByteArray(new File(Filepath));

        // front에서 다운 받기위한 ContentType설정
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
