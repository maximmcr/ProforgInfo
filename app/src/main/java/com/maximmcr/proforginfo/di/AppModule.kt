package com.maximmcr.proforginfo.di

import android.content.Context
import com.maximmcr.proforginfo.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    companion object {
        @Provides
        @Singleton
        fun context(app: App) : Context = app.applicationContext
    }

}
