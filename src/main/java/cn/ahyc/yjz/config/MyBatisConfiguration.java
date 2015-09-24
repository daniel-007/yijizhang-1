package cn.ahyc.yjz.config;

import cn.ahyc.yjz.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * MyBatisConfiguration
 *
 * @author sanlai_lee@qq.com
 */
@Configuration
@MapperScan(basePackages = {"cn.ahyc.yjz.mapper"})
public class MyBatisConfiguration {

      private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisConfiguration.class);


      @Value("${mybatis.configLocation}")
      private String configLocation;

      @Value("${mybatis.mapperLocations}")
      private String mapperLocations;

      @Autowired
      private DataSourceProperties dataSourceProperties;

      /**
       * DataSource
       * @return
       */
      @Bean
      public DataSource dataSource(){
            DataSource dataSource = DataSourceBuilder
                    .create()
                    .driverClassName(this.dataSourceProperties.getDriverClassName())
                    .url(this.dataSourceProperties.getUrl())
                    .username(PasswordUtil.decrypt(this.dataSourceProperties.getUsername()))
                    .password(PasswordUtil.decrypt(this.dataSourceProperties.getPassword()))
                    .build();
            return  dataSource;
      }

      /**
       * DataSourceTransactionManager.
       *
       * @return
       */
      @Bean
      public DataSourceTransactionManager dataSourceTransactionManager() {
            LOGGER.info("Initialize DataSourceTransactionManager with datasource '{}'", dataSource());
            return new DataSourceTransactionManager(dataSource());
      }


      /**
       * SqlSessionFactoryBean.
       *
       * @return
       */
      @Bean
      public SqlSessionFactory sqlSessionFactory() throws Exception {
            LOGGER.info("Initialize SqlSessionFactory...mapperLocations={}", this.mapperLocations);

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

            //设置数据源 datasource
            sqlSessionFactoryBean.setDataSource(dataSource());

            PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
            //设置 configLocation
            LOGGER.info("configLocation={}", configLocation);
            configLocation = StringUtils.isBlank(configLocation) ? "mybatis/mybatis-config.xml" : configLocation;
            Resource[] configLocationRes = pathMatchingResourcePatternResolver.getResources(configLocation);
            if (configLocationRes.length == 0) {
                  LOGGER.warn("Can't found mybatis-config.xml file in path '{}'", configLocation);
            } else {
                  try {
                        Resource resource = configLocationRes[0];
                        LOGGER.info("Found mybatis-config.xml at '{}'", resource.getFile().getCanonicalPath());
                        sqlSessionFactoryBean.setConfigLocation(resource);
                  } catch (IOException e) {
                        e.printStackTrace();
                  }
            }

            //设置 mapperLocations
            mapperLocations = StringUtils.isBlank(mapperLocations) ? "mybatis/mappers/**/*.xml" : mapperLocations;
            Resource[] mapperLocationsRes = pathMatchingResourcePatternResolver.getResources(mapperLocations);
            if (mapperLocationsRes.length == 0) {
                  LOGGER.warn("Set mapperLocations failed. Can not found any file in path '{}'", mapperLocations);
            } else {
                  sqlSessionFactoryBean.setMapperLocations(mapperLocationsRes);
            }
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
            return sqlSessionFactory;
      }

      /**
       * SqlSessionTemplate.
       *
       * @return
       */
      @Bean
      public SqlSessionTemplate sqlSessionTemplate() {
            try {
                  SqlSessionFactory sqlSessionFactory = sqlSessionFactory();
                  LOGGER.info("Initialize SqlSessionTemplate bean with sqlSessionFactory '{}'", sqlSessionFactory);
                  //默认采用Batch方式提交事务
                  SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
                  return sqlSessionTemplate;
            } catch (Exception e) {
                  LOGGER.error("Fail to create bean SqlSessionTemplate. Caused by {}", e.getMessage());
                  e.printStackTrace();
                  return null;
            }
      }
}
