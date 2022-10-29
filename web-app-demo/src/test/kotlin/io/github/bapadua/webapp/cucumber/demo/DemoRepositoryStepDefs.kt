package io.github.bapadua.webapp.cucumber.demo

import io.cucumber.datatable.DataTable
import io.cucumber.java.Before
import io.cucumber.java.DataTableType
import io.cucumber.java.Scenario
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.github.bapadua.webapp.model.entity.DemoEntity
import io.github.bapadua.webapp.repository.DemoRepository
import org.springframework.beans.factory.annotation.Autowired

class DemoRepositoryStepDefs {

    @Autowired
    lateinit var demoRepository: DemoRepository

    private lateinit var demoEntity: DemoEntity

    @DataTableType
    fun transform(table: Map<String, String>): DemoEntity {
        return DemoEntity(
            demoCode = table["code"].toString(),
            demoName = table["name"].toString(),
            demoNumber = table["number"]!!.toLong(),
            null
        )
    }

    @Before
    fun setup(scenario: Scenario) {
        println("##Scenario ${scenario.name} Running on: ${Thread.currentThread().name}")
    }

    @Given("the following payload exists:")
    fun `a valid object exists`(data: DataTable) {
        demoEntity = data.transpose().asList(DemoEntity::class.java).first()
    }

    @When("the payload is saved")
    fun `the payload is saved`() {
        demoEntity = demoRepository.save(demoEntity)
    }

    @Then("the object can be found at the database")
    fun `the object is persisted`() {
        demoRepository.findById(demoEntity.id!!)
    }
}