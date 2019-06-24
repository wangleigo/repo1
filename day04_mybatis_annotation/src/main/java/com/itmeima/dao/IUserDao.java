package com.itmeima.dao;

import com.itmeima.domain.Items;
import com.itmeima.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    /*
    * 查询所有
    * */
    @Select("select * from items")
    List<Items> findAll();



    /*
     * 添加数据
     * */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    @SelectKey(statement = "select last_insert_id()",keyProperty = "id",keyColumn = "id",resultType = Integer.class,before = false)
    void saveUser(User user);
    /*
     * 修改数据
     * */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id} ")
    void updateUser(User user);
    /*
    * 删除数据
    * */
    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);




}
