package pers.darren.springboot.example.service.impl;

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
        simpleMessage.setSubject(subject);
        simpleMessage.setText(message);
        simpleMessage.setTo("luojianlogin@163.com", "luojianlogin@vip.qq.com");
        this.javaMailSender.send(simpleMessage);
    }

    @Override
    public void sendMimeMailMessage(final String subject, final String message) {
        try {
            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject(subject);
            helper.setText(message);
            helper.addTo("luojianlogin@163.com", "163 EMail");
            helper.addTo("luojianlogin@vip.qq.com", "QQ Email");
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