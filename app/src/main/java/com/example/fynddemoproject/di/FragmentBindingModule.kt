package com.example.fynddemoproject.di

import com.example.fynddemoproject.base.BaseAppCompatActivity
import com.example.fynddemoproject.base.BaseFragment
import com.example.fynddemoproject.home.ListFragment
import com.example.fynddemoproject.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): ListFragment


}