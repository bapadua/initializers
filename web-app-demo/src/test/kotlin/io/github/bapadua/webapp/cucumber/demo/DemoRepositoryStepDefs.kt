package io.github.bapadua.webapp.cucumber.demo

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.github.bapadua.webapp.repository.DemoRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired

class DemoRepositoryStepDefs {

    @Autowired
    lateinit var demoRepository: DemoRepository

    companion object {
        private var demoEntity = PayloadStepDefs.demoEntity
    }

    @When("the payload is saved")
    fun `the payload is saved`() {
        demoRepository.save(demoEntity)
        assertNotNull(demoEntity)
    }

    @Then("the object can be found at the database")
    fun `the object is persisted`() {
        demoRepository.findById(demoEntity.id!!)
    }
}