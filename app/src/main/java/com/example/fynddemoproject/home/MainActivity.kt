package com.example.fynddemoproject.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.fynddemoproject.R
import com.example.fynddemoproject.base.BaseAppCompatActivity
import com.example.fynddemoproject.databinding.ActivityMainBinding
import com.example.fynddemoproject.util.AppEnums

class MainActivity : BaseAppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var fragment: Fragment? = null
    var tag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigation.selectedItemId = R.id.action_now_playing
        fragment = ListFragment.newInstance(AppEnums.MovieListType.NOW_PLAYING)
        addFragment(
            fragment,
            animate = false,
            addToBackStack = false,
            tag = tag,
            container = R.id.fragment_container
        )

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            tag = item.title?.toString()
            fragment = when (item.itemId) {
                R.id.action_popular -> {
                    ListFragment.newInstance(AppEnums.MovieListType.POPULAR)
                }
                R.id.action_highest_rate -> {
                    ListFragment.newInstance(AppEnums.MovieListType.TOP_RATED)
                }
                R.id.action_upcoming -> {
                    ListFragment.newInstance(AppEnums.MovieListType.UPCOMING)
                }
                R.id.action_now_playing -> {
                    ListFragment.newInstance(AppEnums.MovieListType.NOW_PLAYING)
                }
                else -> throw RuntimeException("Unknown sortType to replace fragment")
            }
            replaceFragmentWithFadeInTransition(
                fragment,
                animate = false,
                addToBackStack = false,
                tag = tag,
                container = R.id.fragment_container
            )
            true
        }

        initializeAppBar()
    }

    override fun initializeAppBar() {
        super.initializeAppBar()
        setSupportActionBar(binding.toolbarLayout.toolbar)
        supportActionBar?.title = getString(R.string.appTitle)
    }

}