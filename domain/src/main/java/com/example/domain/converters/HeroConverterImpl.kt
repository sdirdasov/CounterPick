package com.example.domain.converters

import com.example.data.remote.models.HeroApi
import com.example.domain.models.Hero

class HeroConverterImpl {
    fun fromApiToUI(model: HeroApi): Hero {
        return Hero(
            id = model.id,
            title = model.name,
            attackType = if (model.attack_type == "Melee") { 0 } else { 1 },
            icon = ""
        )
    }
}