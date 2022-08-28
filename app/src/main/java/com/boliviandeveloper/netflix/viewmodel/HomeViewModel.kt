package com.boliviandeveloper.netflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.boliviandeveloper.netflix.model.entity.response.AuthResponse
import com.boliviandeveloper.netflix.model.entity.response.SectionsResponse
import com.boliviandeveloper.netflix.model.repository.SectionRepository
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sectionRepository: SectionRepository
) : ViewModel() {

    fun getAll() : LiveData<Resource<SectionsResponse?>> {
        return flow {
            emit(Resource.loading())
            emit(sectionRepository.getAll())
        }.asLiveData(Dispatchers.IO)
    }
}