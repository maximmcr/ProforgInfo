package com.maximmcr.proforginfo.di

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.maximmcr.proforginfo.data.local.model.MyObjectBox
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepoModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideDb(context: Context) = MyObjectBox.builder().androidContext(context).build()

        @JvmStatic
        @Provides
        @Singleton
        fun provideFirebaseDb() = FirebaseDatabase.getInstance()
    }


}