# Sample Spring Boot Starter

This library provides a sample auto-configuration project.

## Introduction

As described in the [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-starter),

> Starters are a set of convenient dependency descriptors that you can include in your application. You get a one-stop shop for all the Spring and related technologies that you need without having to hunt through sample code and copy-paste loads of dependency descriptors. For example, if you want to get started using Spring and JPA for database access, include the spring-boot-starter-data-jpa dependency in your project.

## Usage
To utilize, add the starter on the dependencies list.
 
```yaml
dependencies {
	implementation 'org.think.starter:demo-spring-boot-starter'
}
```

After adding the `demo-spring-boot-starter`, you may add and set the following in the `application.properties` file.

 ```
swagger.api.title=
swagger.api.description=
swagger.api.version=v1

swagger.oauth.enabled=true
swagger.oauth.server.authorize.url=

swagger.oauth.client.name=swagger-ui
swagger.oauth.client.id=
swagger.oauth.client.secret=
 ```
 