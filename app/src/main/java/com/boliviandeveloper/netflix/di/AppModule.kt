package com.boliviandeveloper.netflix.di

import android.content.Context
import androidx.room.Room
import com.boliviandeveloper.netflix.BuildConfig
import com.boliviandeveloper.netflix.helper.http.Authorization
import com.boliviandeveloper.netflix.model.repository.datasource.DataBase
import com.boliviandeveloper.netflix.model.repository.datasource.local.SessionDao
import com.boliviandeveloper.netflix.model.repository.datasource.remote.SectionService
import com.boliviandeveloper.netflix.model.repository.datasource.remote.SerieService
import com.boliviandeveloper.netflix.model.repository.datasource.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(context, DataBase::class.java, "netflix").build()

    @Provides
    @Singleton
    fun providesUserDao(dataBase: DataBase) = dataBase.userDao()

    @Provides
    @Singleton
    fun providesSessionDao(dataBase: DataBase) = dataBase.sessionDao()

    @Provides
    @Singleton
    fun providesBaseUrl() = "https://www.api.netflix.boliviandeveloper.com/"

    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun providesAuthorization(sessionDao: SessionDao) = Authorization("NETFLIX", "6624e700f9a2408baeec7d6494d46724", sessionDao)

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

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit) : UserService = retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providesSectionService(retrofit: Retrofit) : SectionService = retrofit.create(SectionService::class.java)

    @Provides
    @Singleton
    fun providesSerieService(retrofit: Retrofit) : SerieService = retrofit.create(SerieService::class.java)
}