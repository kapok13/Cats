package com.vb.cats.cats.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.vb.cats.database.entity.Cat

@JsonIgnoreProperties(ignoreUnknown = true)
data class CatResponse(
    @JsonProperty("id") val id: String,
    @JsonProperty("url") val url: String
)

fun CatResponse.transformToCat() = Cat(this.id, this.url, false)
