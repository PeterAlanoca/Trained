package com.boliviandeveloper.netflix.di

import com.boliviandeveloper.netflix.BuildConfig
import com.boliviandeveloper.netflix.helper.http.Authorization
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBaseUrl() = "https://www.api.netflix.boliviandeveloper.com/"

    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun providesAuthorization() = Authorization("NETFLIX", "6624e700f9a2408baeec7d6494d46724")

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(60, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesConverterFactory() : Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String, converterFactory: Converter.Factory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

}