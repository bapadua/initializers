package io.github.bapadua.webapp

import io.github.bapadua.webapp.controller.DemoController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WebAppDemoApplicationTests {

	@Autowired
	private lateinit var demoController: DemoController

	@Test
	fun contextLoads() {
		Assertions.assertNotNull(demoController)
	}

}
