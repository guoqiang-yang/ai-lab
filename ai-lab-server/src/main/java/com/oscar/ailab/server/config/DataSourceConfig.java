package com.oscar.ailab.server.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(value = "com.oscar.ailab.server.dao.mapper", sqlSessionFactoryRef = "ailabSqlSessionFactory")
@EnableTransactionManagement
public class DataSourceConfig {
//    static final String PACKAGE = "com.oscar.ailab.server.dao.mapper";
//    private static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";


    @Bean("ailabDataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource createDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "ailabTransactionManager")
    public DataSourceTransactionManager mybatisTransactionManager(@Qualifier("ailabDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ailabSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("ailabDataSource") DataSource dataSource) throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(MAPPER_LOCATION));
        sessionFactory.setPlugins(ailabPlusInterceptor());
        return sessionFactory.getObject();
    }

    @Bean("communityyetachatPlusInterceptor")
    public MybatisPlusInterceptor ailabPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


}
