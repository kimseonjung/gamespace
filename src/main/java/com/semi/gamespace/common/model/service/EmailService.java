//package com.semi.gamespace.common.model.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.UnsupportedEncodingException;
//
//@PropertySource("classpath:application-mailsmtp.yml")
//@Service
//public class EmailService {
//    private final JavaMailSender javaMailSender;
//    private final String ePw = createKey(6);
//    @Value("${spring.mail.username}")
//    private String id;
//
//    public EmailService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    public String sendSimpleMessage(String to) throws MessagingException, UnsupportedEncodingException {
//        MimeMessage message = createMessage(to);
//        javaMailSender.send(message);
//
//        return ePw;
//    }
//
//    //인증코드 생성기 (숫자 size자리)
//    private String createKey(int size) {
//        StringBuffer sb = new StringBuffer();
//        for(int i = 0; i < size; i++) {
//            sb.append((int) (Math.random()*10));
//        }
//        return sb.toString();
//    }
//
//    //메일 생성기
//    private MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
//        System.out.println("메일을 전송합니다 - 전송 대상 : " + to + ", 인증 번호 : " + ePw);
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        message.addRecipients(MimeMessage.RecipientType.TO, to);            //메일 보내는 대상
//        message.setSubject("gamespace 이메일 인증을 위한 인증 코드 발송 메일");  //메일 제목
//
//        //메일 본문
//        String msg = "";
//        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
//        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
//        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\">";
//        msg += "<table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\">";
//        msg += "<tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
//        msg += ePw;
//        msg += "</td></tr></tbody></table></div>";
//
//        message.setText(msg, "UTF-8", "html");
//        //내용, charset type, subtype
//        message.setFrom(new InternetAddress(id, "gamespace_ADMIN"));    //보내는 사람 메일 주소
//
//        return message;
//    }
//}
