package com.ando.financial.flipnet.etc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class SessionHelper {

    public static SqlSession getSqlSession(){

        SqlSession sqlSession = null;
        try {
            Resource resource = new ClassPathResource("mybatisconfig.xml");
//            File file = ResourceUtils.getFile("conf/mybatisconfig.xml");
            InputStream inputStream = resource.getInputStream();
            sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sqlSession;
    }

}
