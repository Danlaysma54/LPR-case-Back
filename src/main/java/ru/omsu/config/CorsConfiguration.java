package ru.omsu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures {@link CorsFilter} for the application.
 * <p>
 * Include this configuration to you app, for example, like this:
 * <pre>{@code
 * @Configuration
 * @Import(CorsFilterConfiguration.class)
 * public class CorsConfig {
 * }
 * }</pre>
 * <p>
 * Then you can change allowed origins, methods and headers by defining the environment variables:
 * <dl>
 *     <dt>CORS_ALLOWED_ORIGINS</dt>
 *     <dd>comma separated list of allowed origins, "*" by default which means all origins are allowed (don't do it on production for non-public APIs)</dd>
 *     <dt>CORS_ALLOWED_METHODS</dt>
 *     <dd>comma separated list of allowed methods, "*" by default which means all methods are allowed</dd>
 *     <dt>CORS_ALLOWED_HEADERS</dt>
 *     <dd>comma separated list of allowed headers, "*" by default which means all headers are allowed</dd>
 *     <dt>CORS_ALLOW_CREDENTIALS</dt>
 *     <dd>value for Access-Control-Allow-Credentials response header, "true" by default</dd>
 *     <dt>CORS_PATH</dt>
 *     <dd>path pattern where to apply the CORS filter, "/**" by default</dd>
 * </dl>
 */
@Configuration
public class CorsConfiguration {

    @Value("#{'${CORS_ALLOWED_ORIGINS:*}'.split('\\s*,\\s*')}")
    private final Set<String> allowedOrigins = new HashSet<>(Collections.singleton("*"));

    @Value("#{'${CORS_ALLOWED_METHODS:*}'.split('\\s*,\\s*')}")
    private final Set<String> allowedMethods = new HashSet<>(Collections.singleton("*"));

    @Value("#{'${CORS_ALLOWED_HEADERS:*}'.split('\\s*,\\s*')}")
    private final Set<String> allowedHeaders = new HashSet<>(Collections.singleton("*"));

    @Value("${CORS_ALLOW_CREDENTIALS:true}")
    private boolean allowCredentials = true;

    @Value("${CORS_PATH:/**}")
    private String path = "/**";

    // https://stackoverflow.com/questions/45677048/how-do-i-enable-cors-headers-in-the-swagger-v2-api-docs-offered-by-springfox-sw
    // https://stackoverflow.com/questions/66060750/cors-error-when-using-corsfilter-and-spring-security
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        config.setAllowCredentials(allowCredentials);
        allowedOrigins.forEach(config::addAllowedOriginPattern);
        allowedMethods.forEach(config::addAllowedMethod);
        allowedHeaders.forEach(config::addAllowedHeader);
//        log.info("CORS config: {}", toString(config));

        source.registerCorsConfiguration(path, config);
        return new CorsFilter(source);
    }

    private String toString(final org.springframework.web.cors.CorsConfiguration config) {
        return "CorsConfiguration(" +
                "allowCredentials=" + config.getAllowCredentials() +
                ", allowedOrigins=" + config.getAllowedOrigins() +
                ", allowedMethods=" + config.getAllowedMethods() +
                ", allowedHeaders=" + config.getAllowedHeaders() +
                ")";
    }

}
