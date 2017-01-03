package keystone;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

@Configuration
@PropertySource("classpath:keystone.properties")
@Import(KeystoneConfig.class)
public class KeystoneConfigTest {
    @Autowired
    private Environment environment;

    @Bean
    public SingleConnectionDataSource singleConnectionDataSource() {
        SingleConnectionDataSource singleConnectionDataSource =
                new SingleConnectionDataSource();

        singleConnectionDataSource.setUrl(environment.getProperty("database.url"));
        singleConnectionDataSource.setUsername(environment.getProperty("database.user"));
        singleConnectionDataSource.setPassword("database.password");
        singleConnectionDataSource.setSuppressClose(true);

        return singleConnectionDataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        String changeLogFile = "classpath:db-changelog.xml";

        springLiquibase.setDataSource(singleConnectionDataSource());
        springLiquibase.setChangeLog(changeLogFile);

        return springLiquibase;
    }

}
