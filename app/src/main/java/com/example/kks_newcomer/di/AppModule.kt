package com.example.kks_newcomer.di

import com.example.kks_newcomer.BuildConfig
import com.example.kks_newcomer.data.network.TourApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TOUR_API_HOST = "https://www.travel.taipei/open-api/"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    @Singleton
    @Provides
    @Named("Tour")
    fun provideRetrofitTour(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(TOUR_API_HOST)
            .addConverterFactory(MoshiConverterFactory.create()).client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideTourApi(@Named("Tour") retrofit: Retrofit): TourApi =
        retrofit.create(TourApi::class.java)
}