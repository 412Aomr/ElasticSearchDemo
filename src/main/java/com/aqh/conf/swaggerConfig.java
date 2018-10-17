package com.aqh.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: LHL
 * @Date: 2018/10/9 14:39
 * @Description:
 */
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix="swagger")
public class swaggerConfig {

    // swagger 2 + swagger ui
    private String name;
    private String url;
    private String email;
    private String title;
    private String license;
    private String licenseUrl;
    private String version;
    private String termsOfServiceUrl;


    @Bean
    public Docket getDocket() {
        return     new Docket(DocumentationType.SWAGGER_2).
                apiInfo(getApiInfo()). //放该软件的基本信息，包括名称，作者
                select().  //开始选择我们要扫描那个Controller
                apis(RequestHandlerSelectors.basePackage("com.aqh.controller")).//配置要扫描那个包
                build();
    }

    public ApiInfo getApiInfo() {
        Contact contact = new Contact(name, this.termsOfServiceUrl, this.email);
        return new ApiInfoBuilder().
                contact(contact).
                title(this.title).
                version(this.version).
                termsOfServiceUrl(this.termsOfServiceUrl).
                licenseUrl(this.licenseUrl).
                license(this.license).build();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getLicense() {
        return license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public String getVersion() {
        return version;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }
}
