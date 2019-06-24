package com.itmeima.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itmeima.dao.IUserDao;
import com.itmeima.domain.Items;
import com.itmeima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * mybatis的入门案例
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    /*
     * 资源加载
     * */
    @Before
    public void init() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    /*
     * 释放资源
     * */
    @After
    public void destroy() throws Exception {
        session.commit();

        //6.释放资源
        session.close();
        in.close();
    }
    /*
     * 查询所有
     * */

    /*
     * 添加数据
     * */
    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("鲁智深");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("大名府");
        System.out.println(user);
        userDao.saveUser(user);
        System.out.println(user);
    }

    /*
     * 修改数据
     * */
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(41);
        user.setUsername("柯南");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("东京");
        userDao.updateUser(user);
    }

    /*
     * 删除数据
     * */
    @Test
    public void deleteUser() {
        userDao.deleteUser(43);
    }


    @Test
    public void page() {
        Integer currentPage = 1;
        Integer pageSize = 5;

        PageHelper.startPage(currentPage,pageSize);

        List<Items> all = userDao.findAll();


        PageInfo pageInfo = new PageInfo(all);
        System.out.println("总数"+pageInfo.getTotal());
        System.out.println("页数"+pageInfo.getPages());
        System.out.println("显示数量"+pageInfo.getPageSize());
        System.out.println("当前页"+pageInfo.getPageNum());


    }

    @Test
    public void page1() {
        Integer currentPage = 1;
        Integer pageSize = 5;

        PageHelper.startPage(currentPage,pageSize);

         Page<Items> all = (Page<Items>) userDao.findAll();

        for (Items items : all) {
            System.out.println(items);
        }
        PageInfo pageInfo = new PageInfo(all);
        System.out.println("总数"+all.getTotal());
        System.out.println("页数"+all.getPages());
        System.out.println("当前页"+all.getPageNum());
        System.out.println("显示数量"+all.getPageSize());


    }

}
