package pro.risingsun.push.mailservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.risingsun.push.mailservice.model.EmailDTO;
import pro.risingsun.push.mailservice.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;


/**
 * @Author: TheR1sing3un
 * @Date: 2021/09/21/15:40
 * @Description: 邮件发送服务
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;



    /**
     * 发送带静态资源的邮件(通过web端直接以文件方式走服务器传)
     * @param emailDTO
     * @param multipartFile
     * @param resourceId
     * @return
     */
    @Override
    public boolean sendEmail(EmailDTO emailDTO, MultipartFile multipartFile, int resourceId) {
        return false;
    }


    /**
     * 发送邮件(无附件,无静态资源)
     * @param emailDTO
     * @return
     */
    @Override
    public boolean sendEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发送方
        message.setFrom(from);
        //设置收件方
        message.setTo(emailDTO.getTarget());
        //设置邮件标题
        message.setSubject(emailDTO.getTopic());
        //设置正文
        message.setText(emailDTO.getContent());
        try{
            mailSender.send(message);
            return true;
        }catch (MailException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将MultipartFile转化成File
     * @param multipartFile
     * @return
     */
    private File transferMultipartFileToFile(MultipartFile multipartFile){
        //获取文件名
        String name = multipartFile.getName();
        File file = new File(name);
        try {
            //转化成file
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    private File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送带附件的邮件(通过web端直接以文件方式走服务器传)
     * @param emailDTO
     * @param multipartFile
     * @return
     */
    @Override
    public boolean sendEmail(EmailDTO emailDTO, MultipartFile multipartFile){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            //获取邮件配置helper
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //设置邮件发信人
            helper.setFrom(from);
            //设置收件人
            helper.setTo(emailDTO.getTarget());
            //设置邮件标题
            helper.setSubject(emailDTO.getTopic());
            //设置邮件正文(true表示带有附件)
            helper.setText(emailDTO.getContent(),true);
            //将MultipartFile转化成File
            File multipartFileToFile = MultipartFileToFile(multipartFile);
            FileSystemResource file = new FileSystemResource(multipartFileToFile);
            //设置邮件附件
            helper.addAttachment(file.getFilename(),file);
            //发送邮件
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
