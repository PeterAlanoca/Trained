package com.boliviandeveloper.netflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.boliviandeveloper.netflix.model.entity.User
import com.boliviandeveloper.netflix.model.entity.request.AuthRequest
import com.boliviandeveloper.netflix.model.entity.response.AuthResponse
import com.boliviandeveloper.netflix.model.repository.UserRepository
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getUser() : LiveData<Resource<User?>> {
        return flow {
            emit(Resource.loading())
            emit(userRepository.get())
        }.asLiveData(Dispatchers.IO)
    }
}