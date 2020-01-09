package com.example.counterpick.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.counterpick.R
import com.example.counterpick.adapters.HeroAdapter
import com.example.domain.models.Hero
import com.example.counterpick.presenters.HeroListPresenter
import com.example.counterpick.views.HeroListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), HeroListView {
    private val mAdapter = HeroAdapter()

    @InjectPresenter
    lateinit var heroListPresenter: HeroListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdapter()
        heroListPresenter.fetchHeroes()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerHeroList.layoutManager = layoutManager
        recyclerHeroList.adapter = mAdapter
    }

    // View implementation
    override fun presentHeroes(data: List<Hero>) {
        recyclerHeroList.visibility = View.VISIBLE
        txtHeroListLoading.visibility = View.GONE

        mAdapter.setData(newHeroes = data)
    }

    override fun presentLoading() {
        recyclerHeroList.visibility = View.GONE
        txtHeroListLoading.visibility = View.VISIBLE
    }
}
