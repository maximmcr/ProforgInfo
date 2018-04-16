package com.maximmcr.proforginfo.di

import com.maximmcr.proforginfo.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class
))
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}