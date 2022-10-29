package io.github.bapadua.webapp.repository

import io.github.bapadua.webapp.model.entity.DemoEntity
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class DemoRepositoryTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var demoRepository: DemoRepository

    @AfterEach
    fun tear() {
        demoRepository.deleteAll()
    }


    @Test
    @DisplayName("should find an object by object id")
    fun `should search by id`() {
        val entity = samplePersist("code1", "test", 1)
        val result = demoRepository.findByIdOrNull(entity!!.id)
        assertTrue(entity == result)
    }

    @Test
    @DisplayName("should save an object")
    fun `should persist into database`() {
        val entity = DemoEntity("demoCode", "demoName", 123)
        val result = demoRepository.save(entity)
        assertTrue(result.id != null)
    }


    @Test
    @DisplayName("should delete an object")
    fun `should delete an object`() {
        val demo = samplePersist("code1", "test", 1)
        demoRepository.deleteById(demo!!.id!!)
        val result = demoRepository.findByIdOrNull(demo.id!!)
        assertTrue(result == null)
    }

    private fun samplePersist(code: String, name: String, number: Long): DemoEntity? {
        val demoEntity = DemoEntity(code, name, number)
        val entity = entityManager.persist(demoEntity)
        entityManager.flush()
        assertTrue(entity != null)
        return entity
    }
}