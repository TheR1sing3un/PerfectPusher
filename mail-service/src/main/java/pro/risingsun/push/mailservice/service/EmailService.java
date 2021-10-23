package pro.risingsun.push.mailservice.service;

import org.springframework.web.multipart.MultipartFile;
import pro.risingsun.push.mailservice.model.EmailDTO;

/**
 * @Author: TheR1sing3un
 * @Date: 2021/09/21/15:40
 * @Description:
 */
public interface EmailService {

    boolean sendEmail(EmailDTO emailDTO);

    boolean sendEmail(EmailDTO emailDTO, MultipartFile multipartFile);

    boolean sendEmail(EmailDTO emailDTO,MultipartFile multipartFile,int resourceId);

}
