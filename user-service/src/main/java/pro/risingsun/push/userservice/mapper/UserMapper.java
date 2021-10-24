package pro.risingsun.push.userservice.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.entity.MailConfig;
import pro.risingsun.push.entity.User;
import pro.risingsun.push.model.UserChannelDTO;
import pro.risingsun.push.userservice.model.CpConfigDTO;
import pro.risingsun.push.model.UserInfoDTO;
import pro.risingsun.push.userservice.model.UserMailDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 14:22
 * @description
 */
@Mapper
public interface UserMapper {

    @Select("select id,open_id from tbl_user where open_id = #{openId}")
    User getUserByOpenId(String openId);

    @Insert("insert into tbl_user values(#{id},#{openId},#{wxMpStatus},#{wxCpStatus},#{mailStatus},#{sendKey})")
    void insertUser(User user);

    @Select("select id,wx_mp_status,wx_cp_status,mail_status,send_key from tbl_user where id = #{id}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "wxMpStatus",column = "wx_mp_status",javaType = Boolean.class),
            @Result(property = "wxCpStatus",column = "wx_cp_status",javaType = Boolean.class),
            @Result(property = "mailStatus",column = "mail_status",javaType = Boolean.class),
            @Result(property = "sendKey",column = "send_key"),
            @Result(property = "cpConfig",column = "id",javaType = CpConfig.class,
                    one = @One(select = "pro.risingsun.push.userservice.mapper.CpConfigMapper.getCpConfigById")),
            @Result(property = "mailConfig",column = "id",javaType = MailConfig.class,
                    one = @One(select = "pro.risingsun.push.userservice.mapper.MailConfigMapper.getMailConfigById"))
    })
    UserInfoDTO getUserInfoById(Long id);

    @Select("select count(1) from tbl_user where id = #{id}")
    Boolean checkUserExist(Long id);

    @Update("update tbl_cp_config set corp_id = #{corpId},agent_id = #{agentId},secret = #{secret},target = #{target}")
    void updateCpConfig(CpConfigDTO cpConfigDTO);

    @Insert("insert tbl_mail_config values(#{id},#{mailAddress})")
    void insertEmailAddress(UserMailDTO userMailDTO);

    @Update("update tbl_user set wx_cp_status = #{wxCpStatus} where id = #{userId}")
    void updateCpStatus(CpConfigDTO cpConfigDTO);

    @Select("select wx_mp_status from tbl_user where id = #{id}")
    Boolean getMpStatus(Long id);

    @Select("select mail_status from tbl_user where id = #{id}")
    Boolean getMailStatus(Long id);

    @Select("select count(1) from tbl_mail_config where user_id = #{id}")
    Boolean checkMailConfigExist(Long id);

    @Update("update tbl_user set wx_mp_status = #{status} where id = #{id}")
    void updateMpStatus(@Param("id") Long id,@Param("status") Boolean status);

    @Update("update tbl_user set mail_status = #{status} where id = #{id}")
    void updateMailStatus(@Param("id") Long id,@Param("status") Boolean status);

    @Delete("delete from tbl_mail_config where user_id = #{id}")
    void deleteMailConfig(Long id);

    @Update("update tbl_user set send_key = #{sendKey} where id = #{id}")
    void updateSendKey(@Param(("id")) Long id,@Param("sendKey") String sendKey);

    @Select("select id,open_id,wx_mp_status,wx_cp_status,mail_status,send_key from tbl_user where send_key = #{sendKey}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "openId",column = "open_id"),
            @Result(property = "wxMpStatus",column = "wx_mp_status",javaType = Boolean.class),
            @Result(property = "wxCpStatus",column = "wx_cp_status",javaType = Boolean.class),
            @Result(property = "mailStatus",column = "mail_status",javaType = Boolean.class),
            @Result(property = "sendKey",column = "send_key"),
            @Result(property = "cpConfig",column = "id",javaType = CpConfig.class,
                    one = @One(select = "pro.risingsun.push.userservice.mapper.CpConfigMapper.getCpConfigById")),
            @Result(property = "mailConfig",column = "id",javaType = MailConfig.class,
                    one = @One(select = "pro.risingsun.push.userservice.mapper.MailConfigMapper.getMailConfigById"))
    })
    UserChannelDTO getUserInfoBySendKey(String sendKey);
}
