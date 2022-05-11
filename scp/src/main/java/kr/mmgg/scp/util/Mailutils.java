package kr.mmgg.scp.util;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Mailutils {
    private JavaMailSender javaMailSender;
    private MimeMessage mimeMessage;
    private MimeMessageHelper mimeMessageHelper;

    public Mailutils(JavaMailSender javaMailSender) throws MessagingException {
        this.javaMailSender = javaMailSender;
        mimeMessage = javaMailSender.createMimeMessage();
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    }

    // 보낸 사람 이메일
    public void setFrom(String fromAddress) throws MessagingException {
        mimeMessageHelper.setFrom(fromAddress);
    }

    // 받는 사람 이메일
    public void setTo(String email) throws MessagingException {
        mimeMessageHelper.setTo(email);
    }

    // 제목
    public void setSubject(String subject) throws MessagingException {
        mimeMessageHelper.setSubject(subject);
    }

    // 메일 내용
    public void setText(String text, boolean useHtml) throws MessagingException {
        mimeMessageHelper.setText(text, useHtml);
    }

    // 첨부 파일
    public void setAttach(String displayFileName, String pathToAttachment) throws MessagingException, IOException {
        File file = new ClassPathResource(pathToAttachment).getFile();
        FileSystemResource fsr = new FileSystemResource(file);

        mimeMessageHelper.addAttachment(displayFileName, fsr);
    }

    // 이미지 삽입
    public void setInline(String contentId, String pathToInline) throws MessagingException, IOException {
        File file = new ClassPathResource(pathToInline).getFile();
        FileSystemResource fsr = new FileSystemResource(file);

        mimeMessageHelper.addInline(contentId, fsr);
    }

    // 발송
    public void send() {
        try {
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
