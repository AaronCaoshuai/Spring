package com.aaron.spring.ioc.pureanno;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration//spring-xml 配置
//相当于context:property-placeHolder标签
@PropertySource("classpath:db.properties")
public class JdbcConfig {

    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;
    //注册bean对象,主要用来配置非自定义的bean,
    //比如DruidDataSource,SqlSessionFactory
    @Bean(name = "dataSource")
    public DataSource createDataSource(){
        System.out.println("数据源属性配置开始");
        DataSource ds = new PooledDataSource();
        ((PooledDataSource) ds).setDriver(driver);
        ((PooledDataSource) ds).setUrl(url);
        ((PooledDataSource) ds).setUsername(username);
        ((PooledDataSource) ds).setPassword(password);
        System.out.println("数据源属性配置结束");
        return ds;
    }

}
