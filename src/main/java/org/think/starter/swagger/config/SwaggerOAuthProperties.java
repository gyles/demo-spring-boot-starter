package org.think.starter.swagger.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger.oauth.client")
@Data
public class SwaggerOAuthProperties {

    private final String oauthEndpoint;

    private final boolean enabled;

    private String id;

    private String name;

    private String secret;

    public SwaggerOAuthProperties(@Value("${swagger.oauth.server.authorize.url:}") String oauthEndpoint,
                                  @Value("${swagger.oauth.enabled:false}") boolean enabled) {
        this.oauthEndpoint = oauthEndpoint;
        this.enabled = enabled;
    }

}
