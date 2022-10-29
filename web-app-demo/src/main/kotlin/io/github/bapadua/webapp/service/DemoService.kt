package io.github.bapadua.webapp.service

import io.github.bapadua.webapp.NotFoundException
import io.github.bapadua.webapp.model.entity.DemoEntity
import io.github.bapadua.webapp.repository.DemoRepository
import org.springframework.stereotype.Service

@Service
class DemoService(
    private val demoRepository: DemoRepository
) {

    fun insert(demo: DemoEntity): DemoEntity {
        return demoRepository.save(demo)
    }

    fun find(id: Long): DemoEntity {
        return demoRepository.findById(id).orElseThrow { NotFoundException() }
    }

    fun findAll(): MutableList<DemoEntity> {
        return demoRepository.findAll()
    }
}