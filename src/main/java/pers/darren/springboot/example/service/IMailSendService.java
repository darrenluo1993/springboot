package pers.darren.springboot.example.service;

/**
 * SpringBoot电子邮件发送服务
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jun 4, 2021 11:59:38 AM
 */
public interface IMailSendService {
    /**
     * 发送简单的邮件消息
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 4, 2021 2:59:44 PM
     * @param subject 邮件主题
     * @param message 邮件内容
     */
    void sendSimpleMailMessage(String subject, String message);

    /**
     * 发送Mime邮件消息
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 4, 2021 3:04:02 PM
     * @param subject 邮件主题
     * @param message 邮件内容
     */
    void sendMimeMailMessage(String subject, String message);

    /**
     * 发送Mime邮件消息，带附件
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 7, 2021 5:54:18 PM
     * @param subject 邮件主题
     * @param message 邮件内容
     */
    void sendMimeMailMessageWithAttachments(String subject, String message);
}