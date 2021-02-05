package com.example.fynddemoproject.di

import com.example.fynddemoproject.base.BaseAppCompatActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindBaseActivity(): BaseAppCompatActivity

}