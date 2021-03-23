package com.malefeng.mychat.dao;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.MySqlMapper;


public interface GenericMapper<T> extends BaseMapper<T>, MySqlMapper<T>, ExampleMapper<T> {
}

