package org.think.starter.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger.api")
@Data
public class SwaggerApiProperties {
    private String title;
    private String description;
    private String version;
}
