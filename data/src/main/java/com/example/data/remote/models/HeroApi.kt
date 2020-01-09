package com.example.data.remote.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

@Serializable
data class HeroApi (val id: Int, val name: String, val localized_name: String,
                    val primary_attr: String, val attack_type: String, val roles: List<String>) {

    companion object {
        @UnstableDefault
        fun toObject(stringValue: String): HeroApi {
            return Json.nonstrict.parse(serializer(), stringValue)
        }
    }
}

@UnstableDefault
fun HeroApi.toJson(): String {
    return Json.stringify(HeroApi.serializer(), this)
}