package com.example.fynddemoproject

import android.content.Context
import androidx.multidex.MultiDex
import com.example.fynddemoproject.di.ApplicationComponent
import com.example.fynddemoproject.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class AppController : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val injector: ApplicationComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
        injector.inject(this)
        return injector
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}