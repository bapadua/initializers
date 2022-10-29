package io.github.bapadua.webapp.controller

import io.github.bapadua.webapp.model.entity.DemoEntity
import io.github.bapadua.webapp.service.DemoService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/demo")
class DemoController(private var demoService: DemoService) {

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<DemoEntity> {
        return ResponseEntity.ok(demoService.find(id))
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(): ResponseEntity<MutableList<DemoEntity>> {
        return ResponseEntity.ok(demoService.findAll())
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody demo: DemoEntity): ResponseEntity<DemoEntity> {
        val result = demoService.insert(demo)
        val uri = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(result.id).toUri()
        return ResponseEntity.created(uri).body(result)
    }

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun put(@RequestBody demo: DemoEntity): ResponseEntity<DemoEntity> {
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build()
    }
}
