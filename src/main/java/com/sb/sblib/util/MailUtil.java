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

    public class Mail {

        /** メール */
        private SimpleMailMessage simpleMailMessage;

        public Mail() {
            simpleMailMessage = new SimpleMailMessage();
        }

        public Mail from(String from) {
            simpleMailMessage.setFrom(from);
            return this;
        }

        public Mail to(String... to) {
            simpleMailMessage.setTo(to);
            return this;
        }

        public Mail cc(String... cc) {
            simpleMailMessage.setCc(cc);
            return this;
        }

        public Mail bcc(String... bcc) {
            simpleMailMessage.setBcc(bcc);
            return this;
        }

        public void send(String title, String honbun) {
            simpleMailMessage.setSubject(title);
            simpleMailMessage.setText(honbun);
            mailSender.send(simpleMailMessage);
        }
    }

    public Mail createMail() {
        return new Mail();
    }
}
