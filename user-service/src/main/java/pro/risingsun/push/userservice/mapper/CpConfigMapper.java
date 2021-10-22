package pro.risingsun.push.userservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pro.risingsun.push.entity.CpConfig;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 22:10
 * @description
 */

@Mapper
public interface CpConfigMapper {

    @Select("select user_id,corp_id,agent_id,secret from tbl_cp_config where user_id = #{userId}")
    CpConfig getCpConfigById(Long userId);
}
