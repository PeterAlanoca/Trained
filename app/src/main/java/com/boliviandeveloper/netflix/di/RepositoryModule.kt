package com.boliviandeveloper.netflix.di

import android.content.Context
import com.boliviandeveloper.netflix.helper.http.Authorization
import com.boliviandeveloper.netflix.model.repository.SectionRepository
import com.boliviandeveloper.netflix.model.repository.SerieRepository
import com.boliviandeveloper.netflix.model.repository.UserRepository
import com.boliviandeveloper.netflix.model.repository.datasource.local.SessionDao
import com.boliviandeveloper.netflix.model.repository.datasource.local.UserDao
import com.boliviandeveloper.netflix.model.repository.datasource.remote.SectionService
import com.boliviandeveloper.netflix.model.repository.datasource.remote.SerieService
import com.boliviandeveloper.netflix.model.repository.datasource.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun providesUserRepository(@ApplicationContext context: Context, authorization: Authorization, userDao: UserDao, sessionDao: SessionDao, userService: UserService) =
        UserRepository(context, authorization, userDao, sessionDao, userService)

    @Provides
    fun providesSectionRepository(@ApplicationContext context: Context, authorization: Authorization, sectionService: SectionService) =
        SectionRepository(context, authorization, sectionService)

    @Provides
    fun providesSerieRepository(@ApplicationContext context: Context, authorization: Authorization, serieService: SerieService) =
        SerieRepository(context, authorization, serieService)

}
