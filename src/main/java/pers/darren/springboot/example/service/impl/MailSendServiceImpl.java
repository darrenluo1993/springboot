package pers.darren.springboot.example.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pers.darren.springboot.example.service.IMailSendService;

@Slf4j
@Service
public class MailSendServiceImpl implements IMailSendService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMailMessage(final String subject, final String message) {
        final SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom("darrenluo1993@163.com");
        simpleMessage.setSubject(subject);
        simpleMessage.setText(message);
        simpleMessage.setTo("luojianlogin@163.com");
        simpleMessage.setCc("luojianlogin@vip.qq.com");
        simpleMessage.setBcc("luojianlogin@gmail.com");
        this.javaMailSender.send(simpleMessage);
    }

    @Override
    public void sendMimeMailMessage(final String subject, final String message) {
        try {
            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom("darrenluo1993@163.com", "Darren Luo");
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.addTo("luojianlogin@163.com", "163 Email");
            helper.addCc("luojianlogin@vip.qq.com", "QQ Email");
            helper.addBcc("luojianlogin@gmail.com", "Google Email");
            this.javaMailSender.send(helper.getMimeMessage());
        } catch (final MailException e) {
            log.error(e.getMessage(), e);
        } catch (final MessagingException e) {
            log.error(e.getMessage(), e);
        } catch (final UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendMimeMailMessageWithAttachments(final String subject, final String message) {
        try {
            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("darrenluo1993@163.com", "Darren Luo");
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.addAttachment("lombok.jar", new File("/home/darren/Downloads/IDE/lombok.jar"));
            helper.addAttachment("linux命令行大全.pdf", new File("/home/darren/Downloads/Docs/linux命令行大全.pdf"));
            helper.addTo("luojianlogin@163.com", "163 Email");
            helper.addCc("luojianlogin@vip.qq.com", "QQ Email");
            helper.addBcc("luojianlogin@gmail.com", "Google Email");
            this.javaMailSender.send(helper.getMimeMessage());
        } catch (final MailException e) {
            log.error(e.getMessage(), e);
        } catch (final MessagingException e) {
            log.error(e.getMessage(), e);
        } catch (final UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
    }
}