package io.github.bapadua.webapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebAppDemoApplication

fun main(vararg args: String) {
	runApplication<WebAppDemoApplication>(*args)
}
