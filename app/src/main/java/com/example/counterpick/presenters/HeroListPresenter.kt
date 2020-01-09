package com.example.counterpick.presenters

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.domain.models.Hero
import com.example.counterpick.views.HeroListView
import com.example.domain.converters.HeroConverterImpl
import com.example.domain.repositories.implementations.HeroRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.concurrent.thread

@InjectViewState
class HeroListPresenter: MvpPresenter<HeroListView>() {
    private val heroesRepositoryImpl = HeroRepositoryImpl(
        heroConverter = HeroConverterImpl()
    )

    fun fetchHeroes() {
        viewState.presentLoading()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val heroes = heroesRepositoryImpl.fetchHeroes().await()
                withContext(Dispatchers.Main) {
                    viewState.presentHeroes(data = heroes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}