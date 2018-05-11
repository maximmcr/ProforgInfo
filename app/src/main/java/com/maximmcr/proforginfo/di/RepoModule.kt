package com.maximmcr.proforginfo.di

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
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
        fun provideFirebaseDb() = with(FirebaseDatabase.getInstance()) {
            setPersistenceEnabled(true)
            reference
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideAuthUI() = AuthUI.getInstance()

        @JvmStatic
        @Provides
        @Singleton
        fun provideAuthProviders() = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
        )

        @JvmStatic
        @Provides
        @Singleton
        fun provideFirebaseAuth() = FirebaseAuth.getInstance()
    }

}