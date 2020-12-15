package com.coursework.config;

import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
//@EnableSwagger2
public class SwaggerConfig {
//
@Bean
public OpenAPI customOpenAPI() {
  return new OpenAPI()
          .info(new Info()
                  .title("Курсовая работа по web 3 курс 1 семестр")
                  .description("Интернет магазин по продаже электронной техники для ООО «RusElectro»"));
}

}
