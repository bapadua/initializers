package io.github.bapadua.webapp.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class DemoEntity(
    @JsonProperty("code")
    var demoCode: String,
    @JsonProperty("name")
    var demoName: String,
    @JsonProperty("number")
    var demoNumber: Long,
    @Id @GeneratedValue var id: Long? = null
)
