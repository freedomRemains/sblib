package com.sb.sblib.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailUtil {

	/** メール送信 */
	private final JavaMailSender mailSender;

	/** メール */
	private SimpleMailMessage simpleMailMessage;

	public MailUtil createMail() {
		simpleMailMessage = new SimpleMailMessage();
		return this;
	}

	public MailUtil from(String from) {
		simpleMailMessage.setFrom(from);
		return this;
	}

	public MailUtil to(String... to) {
		simpleMailMessage.setTo(to);
		return this;
	}

	public MailUtil cc(String... cc) {
		simpleMailMessage.setCc(cc);
		return this;
	}

	public MailUtil bcc(String... bcc) {
		simpleMailMessage.setBcc(bcc);
		return this;
	}

	public void send(String title, String honbun) {
		simpleMailMessage.setSubject(title);
		simpleMailMessage.setText(honbun);
		mailSender.send(simpleMailMessage);
	}
}
