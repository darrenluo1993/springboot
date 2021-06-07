package pers.darren.springboot.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.darren.springboot.example.service.IMailSendService;

@RestController
@RequestMapping("/mailSend")
public class MailSendController {

    @Autowired
    private IMailSendService mailSendService;

    @PostMapping("/sendSimpleMailMessage")
    public void sendSimpleMailMessage(@RequestParam final String subject, @RequestParam final String message) {
        this.mailSendService.sendSimpleMailMessage(subject, message);
    }

    @PostMapping("/sendMimeMailMessage")
    public void sendMimeMailMessage(@RequestParam final String subject, @RequestParam final String message) {
        this.mailSendService.sendMimeMailMessage(subject, message);
    }

    @PostMapping("/sendMimeMailMessageWithAttachments")
    public void sendMimeMailMessageWithAttachments(@RequestParam final String subject, @RequestParam final String message) {
        this.mailSendService.sendMimeMailMessageWithAttachments(subject, message);
    }
}