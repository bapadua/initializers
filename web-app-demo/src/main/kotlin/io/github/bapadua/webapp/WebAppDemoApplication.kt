package io.github.bapadua.webapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebAppDemoApplication

fun main(args: Array<String>) {
	runApplication<WebAppDemoApplication>(*args)
}
