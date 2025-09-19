package com.nt.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailsUtils 
{
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body,String to,File file)
	{
		try
		{
			MimeMessage mimeMsg = mailSender.createMimeMessage();
			
			MimeMessageHelper helper= new MimeMessageHelper(mimeMsg,true);
			helper.setSubject(subject);
			helper.setText(body,true);
			helper.addAttachment("Plans Info", file);
			helper.setTo(to);
		
			mailSender.send(mimeMsg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
