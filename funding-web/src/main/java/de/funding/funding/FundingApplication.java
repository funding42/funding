package de.funding.funding;

import de.funding.funding.core.repository.VoteRepository;
import de.funding.funding.core.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FundingApplication {

  public static void main(String[] args) {
    SpringApplication.run(FundingApplication.class, args);
  }

  @Autowired
  private VoteRepository voteRepository;

  @Bean
  public Docket petApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(false)
            //.enableUrlTemplating(true)
            ;
  }


  @EnableWebSecurity
  public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
              .anonymous()
              .and()
              .httpBasic();
    }
  }

  @Bean
  public DefaultConversionService defaultConversionService() {
    return new DefaultConversionService();
  }

  @Bean
  public VoteService voteService() {
    return new VoteService(voteRepository);
  }

  @Configuration
  @EnableWebMvc
  public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**").allowedOrigins("localhost", "localhost:8080", "*").allowCredentials(true).allowedMethods("GET", "OPTIONS", "POST", "PUT");
    }
  }

}