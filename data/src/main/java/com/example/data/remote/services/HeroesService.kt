package com.example.data.remote.services

import com.example.data.remote.models.HeroApi
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HeroesService {

    @GET("api/heroes")
    fun getHeroes(): Deferred<List<HeroApi>>
}