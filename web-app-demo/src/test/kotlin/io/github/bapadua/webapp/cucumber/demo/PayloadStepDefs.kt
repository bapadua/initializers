package io.github.bapadua.webapp.cucumber.demo

import io.cucumber.datatable.DataTable
import io.cucumber.java.DataTableType
import io.cucumber.java.en.Given
import io.github.bapadua.webapp.model.entity.DemoEntity
import org.junit.jupiter.api.Assertions

open class PayloadStepDefs {

    companion object {
        lateinit var demoEntity: DemoEntity
    }

    @Given("the following payload exists:")
    fun `a valid object exists`(data: DataTable) {
        demoEntity = data.transpose().asList(DemoEntity::class.java).first()
        Assertions.assertNotNull(demoEntity)
    }

    @DataTableType
    @SuppressWarnings("unused")
    fun transform(table: Map<String, String>): DemoEntity {
        return DemoEntity(
            demoCode = table["code"].toString(),
            demoName = table["name"].toString(),
            demoNumber = table["number"]!!.toLong(),
            null
        )
    }

}
