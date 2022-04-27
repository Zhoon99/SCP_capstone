package kr.mmgg.scp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class TestFileUploadControllerTest {
    @Autowired
    private MockMvc mvc;

    private String name = "test";
    private String contenType = "image/png";
    private String path = "D:/jamin/smartstore/photo/testFileupload.png";

    @Test
    @Order(1)
    @DisplayName("file test")
    public void testTestfileUpload() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", name, contenType, new FileInputStream(path));
        System.out.println("multipartfile : " + multipartFile);
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) mvc
                .perform(multipart("/testfile")
                        .file(multipartFile))
                .andExpect(status().isOk())
                .andReturn().getRequest();
    }
}
