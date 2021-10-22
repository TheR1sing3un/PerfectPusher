package pro.risingsun.push.userservice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pro.risingsun.push.entity.User;

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

}
