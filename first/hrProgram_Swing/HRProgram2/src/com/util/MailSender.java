package com.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

//구글이 제공해주는 구글메일서버를 이용한 자바 메일 발송 프로그램
public class MailSender {
	String myAccountMail="blackb65@gmail.com"; //내가 사용중인 Gmail
	String password = "ktsjynbrfmezmmou";
	
	public void sendMail(String to) {
		String subject ="주문해주셔서 감사합니다"; //메일제목
		String from = "blackb65@gmail.com"; //보낸사람 이메일
		String fromName = "쇼핑몰관리자";//보낸 사람
		//String to ="bully1004@naver.com"; //받을사람 이메일. (콤마로 다수에게 보내는것 가능)
		
		StringBuffer content = new StringBuffer(); //문자의 양이 많기 때문에 버퍼로간다
		content.append("<h1>고객님 안녕하세요, 쇼핑닷컴입니다</h1>");
		content.append("<p>입금이 아직 안되어있네요..</p>");
		
		//메일 설정 - 암기줄
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //mail.smtp 메일을 보낼때 쓰는 프로토콜, 보내는 메일의 host는 바로 gmail서버를 사용하겠다
		props.put("mail.smtp.port", "587"); //포트번호 설정
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");//보안인증방법은 TLS사용
		
		//구글메일을 사용하기 위한 인증절차
		//java메일 api에서 지원하는 Session객체 생성
		Session mailSession = Session.getInstance(props, new Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {
			
				return new PasswordAuthentication(myAccountMail,password);
			}
		});
		
		//보내기 위한 세팅
		MimeMessage message= new MimeMessage(mailSession);
		
		//보내는 사람, 받는 사람 정보 세팅
		try {
			message.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject); //메일제목 설정
			message.setContent(content.toString(),"text/html;charset=UTF-8");
			message.setSentDate(new Date());//보낸 날짜
			
			//전송하기
			Transport trans= mailSession.getTransport("smtp");
			trans.connect(myAccountMail,password);
			trans.sendMessage(message, message.getAllRecipients());
			trans.close();
			System.out.println("메일 발송 성공");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/*
	public static void main(String[] args) {
		MailSender sender = new MailSender();
		sender.sendMail();
	}*/
}
