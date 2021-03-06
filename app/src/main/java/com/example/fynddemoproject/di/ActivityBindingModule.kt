package com.example.fynddemoproject.di

import com.example.fynddemoproject.base.BaseAppCompatActivity
import com.example.fynddemoproject.detail.DetailActivity
import com.example.fynddemoproject.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindBaseActivity(): BaseAppCompatActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity


}