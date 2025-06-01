package ru.omsu.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/**
 * class for configuration db
 */
@Configuration
public class LPRConfiguration {
    /**
     *
     * @return dataSource
     */
    @Bean
    @FlywayDataSource
    @Qualifier("lprDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource lprDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     *
     * @param lprDataSource for connections to the physical data source
     * @return jdbcOperations
     */
    @Bean
    @Qualifier("lprJdbcOperations")
    public JdbcOperations lprJdbcOperations(
            @Qualifier("lprDataSource") final DataSource lprDataSource
    ) {
        return new JdbcTemplate(lprDataSource);
    }

}
