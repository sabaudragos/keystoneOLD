package app.config;

import keystone.KeystoneScannable;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.annotation.Resource;

/**
 * Spring configuration class. The project uses JavaConfig instead of XML based Spring configuration
 */

@Configuration
@PropertySource("classpath:keystone.properties")
@ComponentScan(basePackageClasses = {KeystoneScannable.class})
public class KeystoneConfig {
    @Resource
    private Environment environment;

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public SingleConnectionDataSource singleConnectionDataSource() {
        SingleConnectionDataSource singleConnectionDataSource = new SingleConnectionDataSource();

        singleConnectionDataSource.setUrl(environment.getProperty("database.url"));
        singleConnectionDataSource.setUsername(environment.getProperty("database.user"));
        singleConnectionDataSource.setPassword("database.password");

        return singleConnectionDataSource;
    }
}
