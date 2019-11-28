package Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyBatis {
    private final static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            try {
                properties.load(new BufferedInputStream(new FileInputStream("conf/config")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,properties);
    }
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

}
