package io.github.bapadua.webapp.repository

import io.github.bapadua.webapp.model.entity.DemoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DemoRepository : JpaRepository<DemoEntity, Long> {}
