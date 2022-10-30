package io.github.bapadua.webapp.cucumber.demo

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.github.bapadua.webapp.model.entity.DemoEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI

class DemoControllerStepDefs {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @LocalServerPort
    lateinit var port: Number

    private lateinit var response: ResponseEntity<*>

    companion object {
        const val BASE_URL = "http://localhost"
        var demoEntity = PayloadStepDefs.demoEntity
    }

    @Given("the user send a new post request")
    fun `the user send a new post request`() {
        val uri = getUri()
        val body = HttpEntity<DemoEntity>(demoEntity)
        response = testRestTemplate.exchange(uri, HttpMethod.POST, body, DemoEntity::class.java)
        assertNotNull(response)
        assertNotNull(demoEntity)
    }

    @Then("the server responds with {string} status")
    fun `the server responds`(status: String) {
        assertEquals(HttpStatus.valueOf(status.toInt()), response.statusCode)
    }

    private fun getUri(): URI {
        return URI("$BASE_URL:$port/api/demo")
    }


    private fun getUri(path: String): URI {
        return URI("$BASE_URL:$port/api/demo$path")
    }

}