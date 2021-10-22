package pro.risingsun.push.userservice.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.entity.MailConfig;
import pro.risingsun.push.entity.User;
import pro.risingsun.push.userservice.model.UserInfoDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 14:22
 * @description
 */
@Mapper
public interface UserMapper {

    @Select("select id,open_id from tbl_user where open_id = #{openId}")
    User getUserByOpenId(String openId);

    @Insert("insert into tbl_user values(#{id},#{openId},#{wxMpStatus},#{wxCpStatus},#{mailStatus})")
    void insertUser(User user);

    @Select("select id,wx_mp_status,wx_cp_status,mail_status from tbl_user where id = #{id}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "openId",column = "open_id"),
            @Result(property = "wxMpStatus",column = "wx_mp_status"),
            @Result(property = "wxCpStatus",column = "wx_cp_status"),
            @Result(property = "mailStatus",column = "mail_status"),
            @Result(property = "cpConfig",column = "id",javaType = CpConfig.class,
                    one = @One(select = "pro.risingsun.push.userservice.mapper.CpConfigMapper.getCpConfigById")),
            @Result(property = "mailConfig",column = "id",javaType = MailConfig.class,
                    one = @One(select = "pro.risingsun.push.userservice.mapper.MailConfigMapper.getMailConfigById"))
    })
    UserInfoDTO getUserInfoById(Long id);

    @Select("select count(1) from tbl_user where id = #{id}")
    Boolean checkUserExist(Long id);

    @Update("update tbl_cp_config set corp_id = #{corpId},agent_id = #{agentId},secret = #{secret}")
    void updateCpConfig(CpConfig config);

}
