package com.sh2zqp.mybatis;

import com.sh2zqp.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws Exception {
        // 指定全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // add
//            Category category1 = new Category();
//            category1.setName("新增加的Category");
//            sqlSession.insert("insertaddCategory", category1);

            // delete
//            Category c = new Category();
//            c.setId(2);
//            sqlSession.delete("deleteCategory", c);


            // select
//            Category c= sqlSession.selectOne("getCategory",3);

            // update
            Category c= sqlSession.selectOne("getCategory",3);
            c.setName("修改了的Category名稱");
            sqlSession.update("updateCategory",c);

            listAll(sqlSession);

            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }
    private static void listAll(SqlSession session) {
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

}
