package com.luismipalos.delivery.core.dependencyinjection

import com.luismipalos.delivery.details.data.network.DetailsClient
import com.luismipalos.delivery.homescreen.data.network.HomeClient
import com.luismipalos.delivery.signin.data.network.SignInClient
import com.luismipalos.delivery.signup.data.network.SignUpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://clutrtqceergafdtqjeb.supabase.co/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideSignInClient(retrofit: Retrofit): SignInClient =
        retrofit.create(SignInClient::class.java)

    @Singleton
    @Provides
    fun provideSignUpClient(retrofit: Retrofit): SignUpClient =
        retrofit.create(SignUpClient::class.java)

    @Singleton
    @Provides
    fun provideHomeClient(retrofit: Retrofit): HomeClient =
        retrofit.create(HomeClient::class.java)

    @Singleton
    @Provides
    fun provideDetailsClient(retrofit: Retrofit): DetailsClient =
        retrofit.create(DetailsClient::class.java)
}