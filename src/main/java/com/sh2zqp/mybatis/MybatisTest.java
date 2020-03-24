package com.sh2zqp.mybatis;

import com.sh2zqp.pojo.Category;
import com.sh2zqp.pojo.Order;
import com.sh2zqp.pojo.OrderItem;
import com.sh2zqp.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//            Category c= sqlSession.selectOne("getCategory",3);
//            c.setName("修改了的Category名稱");
//            sqlSession.update("updateCategory",c);

            //            listAll(sqlSession);

            // 模糊查询
//            List<Category> cs = sqlSession.selectList("listCategoryByName","了的");
//            for (Category c : cs) {
//                System.out.println(c.getName());
//            }

            // 多条件查询
//            Map<String,Object> params = new HashMap<String, Object>();
//            params.put("id", 1);
//            params.put("name", "cat");
//            List<Category> cs = sqlSession.selectList("listCategoryByIdAndName",params);
//            for (Category c : cs) {
//                System.out.println(c);
//            }

            // 一对多查询， 分类对应产品
//            List<Category> cs = sqlSession.selectList("listCategory");
//            for (Category c : cs) {
//                System.out.println(c);
//                List<Product> ps = c.getProducts();
//                for (Product p : ps) {
//                    System.out.println("\t"+p);
//                }
//            }

            // 多对一查询，产品对应分类
//            List<Product> ps = sqlSession.selectList("listProduct");
//            for (Product p : ps) {
//                System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
//            }

            // 多对多操作，一张订单里 可以包含多种产品; 一种产品 可以出现在多张订单里
            // 查询关系
            listOrder(sqlSession);
            // 建立关系
            addOrderItem(sqlSession);
            // 删除关系
            deleteOrderItem(sqlSession);
            // 修改关系
            // 多对多不存在修改关系的做法，就是删除旧的，然后新增一条即达到修改的效果。

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

    private static void listOrder(SqlSession session) {
        List<Order> os = session.selectList("listOrder");
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois= o.getOrderItems();
            for (OrderItem oi : ois) {
                System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
            }
        }
    }

    private static void addOrderItem(SqlSession session) {
        Order o1 = session.selectOne("getOrder", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        oi.setNumber(200);

        session.insert("addOrderItem", oi);
    }

    private static void deleteOrderItem(SqlSession session) {
        Order o1 = session.selectOne("getOrder",1);
        Product p6 = session.selectOne("getProduct",6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        session.delete("deleteOrderItem", oi);
    }

}
