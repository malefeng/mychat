package com.malefeng.mychat.dao.mapper;

import com.malefeng.mychat.bean.entity.UserEntity;
import com.malefeng.mychat.dao.GenericMapper;
import org.apache.ibatis.annotations.*;

/**
 * @ClassName UserDao
 * @Author malf
 * @Date 2020/12/16 11:19
 * @Version 1.0
 **/
public interface UserDao extends GenericMapper<UserEntity> {

    @Select("select * from t_user where id = #{id}")
    UserEntity detail(Integer id);
}
