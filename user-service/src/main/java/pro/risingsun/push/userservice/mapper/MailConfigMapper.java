package pro.risingsun.push.userservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pro.risingsun.push.entity.MailConfig;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 22:21
 * @description
 */
@Mapper
public interface MailConfigMapper {

    @Select("Select user_id,mail_address from tbl_mail_config where user_id = #{userId}")
    MailConfig getMailConfigById(Long userId);

}
