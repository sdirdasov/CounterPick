package com.example.data.remote.providers

import com.example.data.remote.helpers.RetrofitFactory
import com.example.data.remote.models.HeroApi
import kotlinx.coroutines.Deferred

class HeroProviderImpl {

    fun getHeroesList(): Deferred<List<HeroApi>> {
        return RetrofitFactory.getHeroesService().getHeroes()
    }
}