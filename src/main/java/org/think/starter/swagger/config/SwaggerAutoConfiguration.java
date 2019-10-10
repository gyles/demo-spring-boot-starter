package org.think.starter.swagger.config;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ImplicitGrantBuilder;
import springfox.documentation.builders.LoginEndpointBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableConfigurationProperties({SwaggerApiProperties.class, SwaggerOAuthProperties.class})
@EnableSwagger2
public class SwaggerAutoConfiguration {

    private final SwaggerApiProperties apiProperties;
    private final SwaggerOAuthProperties oauthProperties;

    public SwaggerAutoConfiguration(SwaggerApiProperties apiProperties, SwaggerOAuthProperties oauthProperties) {
        this.apiProperties = apiProperties;
        this.oauthProperties = oauthProperties;
    }

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
                .select()
                .apis(not(RequestHandlerSelectors.basePackage("org.springframework")))
                .paths(PathSelectors.any())
                .build().forCodeGeneration(true);
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(apiKey());
        if (oauthProperties.isEnabled()) {
            securitySchemes.add(oauth());
        }

        return securitySchemes;
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private OAuth oauth() {
        return new OAuthBuilder()
                .name("oauth2")
                .grantTypes(newArrayList(implicitGrant()))
                .build();
    }

    private GrantType implicitGrant() {
        return new ImplicitGrantBuilder()
                .loginEndpoint(new LoginEndpointBuilder().url(oauthProperties.getOauthEndpoint()).build())
                .build();
    }

    @Bean
    @ConditionalOnMissingBean(SecurityConfiguration.class)
    SecurityConfiguration security() {
        return new SecurityConfiguration(oauthProperties.getId(), oauthProperties.getSecret(),
                oauthProperties.getName(), oauthProperties.getName(),
                "", ApiKeyVehicle.HEADER, "Authorization", ",");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiProperties.getTitle())
                .version(apiProperties.getVersion())
                .description(apiProperties.getDescription())
                .build();
    }
}