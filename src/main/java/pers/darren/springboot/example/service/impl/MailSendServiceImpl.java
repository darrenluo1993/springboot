package pers.darren.springboot.example.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import pers.darren.springboot.example.model.Product;
import pers.darren.springboot.example.service.IMailSendService;

@Slf4j
@Service
public class MailSendServiceImpl implements IMailSendService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration configuration;

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
        } catch (final MessagingException e) {
            log.error(e.getMessage(), e);
        } catch (final UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendMimeMailMessageWithInline(final String subject, final String message) {
        try {
            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("darrenluo1993@163.com", "Darren Luo");
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.addInline("Picture_16_Taste", new File("/home/darren/Pictures/Background/Picture_16_Taste.jpg"));
            helper.addInline("Picture_18_Camera", new File("/home/darren/Pictures/Background/Picture_18_Camera.jpg"));
            helper.addTo("luojianlogin@163.com", "163 Email");
            helper.addCc("luojianlogin@vip.qq.com", "QQ Email");
            helper.addBcc("luojianlogin@gmail.com", "Google Email");
            this.javaMailSender.send(helper.getMimeMessage());
        } catch (final MessagingException e) {
            log.error(e.getMessage(), e);
        } catch (final UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendMimeMailMessageWithAttachmentsAndInline(final String subject, final String message) {
        try {
            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("darrenluo1993@163.com", "Darren Luo");
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.addInline("Picture_16_Taste", new File("/home/darren/Pictures/Background/Picture_16_Taste.jpg"));
            helper.addInline("Picture_18_Camera", new File("/home/darren/Pictures/Background/Picture_18_Camera.jpg"));
            helper.addAttachment("lombok.jar", new File("/home/darren/Downloads/IDE/lombok.jar"));
            helper.addAttachment("linux命令行大全.pdf", new File("/home/darren/Downloads/Docs/linux命令行大全.pdf"));
            helper.addTo("luojianlogin@163.com", "163 Email");
            helper.addCc("luojianlogin@vip.qq.com", "QQ Email");
            helper.addBcc("luojianlogin@gmail.com", "Google Email");
            this.javaMailSender.send(helper.getMimeMessage());
        } catch (final MessagingException e) {
            log.error(e.getMessage(), e);
        } catch (final UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendMimeMailMessageViaMimeMessagePreparator(final String subject, final String message) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("darrenluo1993@163.com", "Darren Luo");
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.addInline("Picture_16_Taste", new File("/home/darren/Pictures/Background/Picture_16_Taste.jpg"));
            helper.addAttachment("linux命令行大全.pdf", new File("/home/darren/Downloads/Docs/linux命令行大全.pdf"));
            helper.addTo("luojianlogin@163.com", "163 Email");
            helper.addCc("luojianlogin@vip.qq.com", "QQ Email");
            helper.addBcc("luojianlogin@gmail.com", "Google Email");
        };
        this.javaMailSender.send(preparator);
    }

    @Override
    public void sendMimeMailMessageWithFreeMarkerTemplate(final String subject) {
        try {
            // 创建数据模型
            final Map<String, Object> root = new HashMap<>();
            root.put("user", "Darren Luo");
            final Map<String, Object> latestProduct = new HashMap<>();
            latestProduct.put("name", "Orange");
            latestProduct.put("url", "/products/Orange.html");
            root.put("latestProduct", latestProduct);
            final List<Product> productList = new ArrayList<>(4);
            productList.add(new Product("产品一", new BigDecimal(1000.5), 10000, "产品一的描述", "Darren Luo", new Date()));
            productList.add(new Product("产品二", new BigDecimal(1001.5), 10001, "产品二的描述", "Darren Luo", new Date()));
            productList.add(new Product("产品三", new BigDecimal(1002.5), 10002, "产品三的描述", "Darren Luo", new Date()));
            productList.add(new Product("产品四", new BigDecimal(1003.5), 10003, "产品四的描述", "Darren Luo", new Date()));
            root.put("productList", productList);

            final Template template = this.configuration.getTemplate("productIntroduction.ftlh");
            final StringWriter writer = new StringWriter();
            template.process(root, writer);
            final String message = writer.toString();

            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("darrenluo1993@163.com", "Darren Luo");
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.addTo("luojianlogin@163.com", "163 Email");
            helper.addCc("luojianlogin@vip.qq.com", "QQ Email");
            helper.addBcc("luojianlogin@gmail.com", "Google Email");
            this.javaMailSender.send(helper.getMimeMessage());
        } catch (final TemplateException e) {
            log.error(e.getMessage(), e);
        } catch (final MessagingException e) {
            log.error(e.getMessage(), e);
        } catch (final UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}