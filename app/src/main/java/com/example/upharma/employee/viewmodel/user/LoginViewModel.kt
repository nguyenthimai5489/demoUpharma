package com.example.upharma.employee.viewmodel.user


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.upharma.common.Resource
import com.example.upharma.model.entity.NPPGuestInfo
import com.example.upharma.model.entity.User
import com.example.upharma.model.res.guest.NPPGuestLoginRes
import com.example.upharma.model.res.user.STUserLoginRes
import com.example.upharma.repository.UserRepository

import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {


    val mediatorliveLoginResource = MediatorLiveData<Resource<STUserLoginRes>>()
    private lateinit var liveLoginResource: LiveData<Resource<STUserLoginRes>>

    fun login(userName: String,password: String) {
        liveLoginResource = userRepository.login(userName,password)
        mediatorliveLoginResource.removeSource(liveLoginResource)
        mediatorliveLoginResource.addSource(liveLoginResource) {
            mediatorliveLoginResource.value = it
        }
    }

    fun insertToRoom(user: User) = userRepository.insertToRoom(user)

    //fun deleteRoom() = userRepository.deleteRoom()
}
