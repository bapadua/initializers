package io.github.bapadua.webapp.integrated

import io.github.bapadua.webapp.WebAppDemoApplication
import io.github.bapadua.webapp.model.entity.DemoEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import java.net.URI

@SpringBootTest(
    classes = [WebAppDemoApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class DemoControllerIntegrated {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @LocalServerPort
    lateinit var port: Number

    companion object {
        const val URL = "http://localhost"
        const val PATH = "/api/demo"
    }


    @Test
    fun `should success for posting`() {
        val uri = getUrl()
        val body = DemoEntity("1234", "test", 123)
        val request = HttpEntity<DemoEntity>(body)
        val response = testRestTemplate.exchange(uri, HttpMethod.POST, request, DemoEntity::class.java)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(body.demoCode, response.body!!.demoCode)
        assertEquals(body.demoName, response.body!!.demoName)
        assertEquals(body.demoNumber, response.body!!.demoNumber)
    }


    @Test
    fun `should success getting all data`() {
        val result = testRestTemplate.exchange(
            getUrl(),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<DemoEntity>>() {})
        assertEquals(HttpStatus.OK, result.statusCode)
    }

    @Test
    fun `should find object by id`() {
        val url = getUrl("/1")
        val result = testRestTemplate.exchange(url, HttpMethod.GET, null, String::class.java)
        assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
    }

    @Test
    fun `should delete an object`() {
        val url = getUrl("/2")
        val result = testRestTemplate.exchange(url, HttpMethod.DELETE, null, String::class.java)
        assertEquals(HttpStatus.NO_CONTENT, result.statusCode)
    }

    private fun getUrl(): URI {
        return URI("$URL:$port$PATH")
    }

    private fun getUrl(pathVariable: String): URI {
        return URI("$URL:$port$PATH$pathVariable")
    }
}