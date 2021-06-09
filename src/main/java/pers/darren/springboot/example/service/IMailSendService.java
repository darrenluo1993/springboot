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

    /**
     * 发送Mime邮件消息，邮件内容嵌入图片
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 8, 2021 11:45:09 AM
     * @param subject 邮件主题
     * @param message 邮件内容
     */
    void sendMimeMailMessageWithInline(String subject, String message);

    /**
     * 发送Mime邮件消息，带附件，邮件内容嵌入图片
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 9, 2021 9:37:17 AM
     * @param subject 邮件主题
     * @param message 邮件内容
     */
    void sendMimeMailMessageWithAttachmentsAndInline(String subject, String message);

    /**
     * 发送Mime邮件消息，使用MimeMessagePreparator
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 9, 2021 11:23:19 AM
     * @param subject 邮件主题
     * @param message 邮件内容
     */
    void sendMimeMailMessageViaMimeMessagePreparator(String subject, String message);

    /**
     * 发送Mime邮件消息，使用FreeMarker模板
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 9, 2021 11:25:47 AM
     * @param subject 邮件主题
     */
    void sendMimeMailMessageWithFreeMarkerTemplate(String subject);
}