package io.github.bapadua.webapp.cucumber.demo

import io.cucumber.spring.CucumberContextConfiguration
import io.github.bapadua.webapp.WebAppDemoApplication
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [WebAppDemoApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@CucumberContextConfiguration
class CucumberContextConfig {
}