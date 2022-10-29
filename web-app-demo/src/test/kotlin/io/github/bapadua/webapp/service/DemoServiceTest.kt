package io.github.bapadua.webapp.service

import io.github.bapadua.webapp.model.entity.DemoEntity
import io.github.bapadua.webapp.repository.DemoRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.Optional

internal class DemoServiceTest {

    private lateinit var demoRepositoryMock: DemoRepository
    private lateinit var demoService: DemoService
    private lateinit var demo: DemoEntity

    @BeforeEach
    fun setup() {
        demo = DemoEntity(
            "demoCode",
            "demoName",
            123L,
            1L
        )
        demoRepositoryMock = mock {
            onGeneric { save(any()) } doReturn demo
            onGeneric { findAll() } doReturn mutableListOf(demo)
            on { findById(any())} doReturn Optional.of(demo)
        }
        demoService = DemoService(
            demoRepositoryMock
        )
    }

    @Nested
    inner class FindById {

        @Test
        @DisplayName("should find an entity by id")
        fun shouldFindById() {
            val id = 1L
            demoService.find(id)
            verify(demoRepositoryMock, times(1)).findById(id)
        }
    }

    @Nested
    inner class FindAllEntities {

        @Test
        @DisplayName("should return all data")
        fun shouldRetrieveAllEntities() {
            demoService.findAll()
            verify(demoRepositoryMock, times(1)).findAll()
        }
    }

    @Nested
    inner class SaveAnEntity {

        @Test
        @DisplayName("should insert an entity to db")
        fun shouldSaveAnEntity() {
            val captor = argumentCaptor<DemoEntity>()
            demoService.insert(demo)
            verify(demoRepositoryMock, times(1)).save(captor.capture())
            assertNotNull(captor.firstValue)
            assertEquals(demo.demoCode, captor.firstValue.demoCode)
            assertEquals(demo.demoName, captor.firstValue.demoName)
            assertEquals(demo.demoNumber, captor.firstValue.demoNumber)
        }
    }
}