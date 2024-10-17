package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//@Configuration
//public class LPRConfiguration {
//    @Bean
//    @Qualifier("lprDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.lpr")
//    public DataSource lprDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean
//    @Qualifier("lprJdbcOperations")
//    public JdbcOperations lprJdbcOperations(
//            @Qualifier("lprDataSource") final DataSource quizDataSource
//    ) {
//        return new JdbcTemplate(quizDataSource);
//    }
//
//}
