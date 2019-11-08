package com.example.upharma.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.upharma.common.ApiResponse

import com.example.upharma.common.AppExecutors
import com.example.upharma.common.NetworkBoundResource
import com.example.upharma.common.Resource
import com.example.upharma.dao.UserDAO
import com.example.upharma.model.entity.NPPGuestInfo
import com.example.upharma.model.res.guest.NPPGuestLoginRes
import com.example.upharma.model.res.user.STUserLoginRes
import com.example.upharma.utilities.runOnIoThread
import com.example.upharma.webservice.WebService

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDAO: UserDAO,
    private val webservice: WebService,
    private val appExecutors: AppExecutors
)
{

    //private var userByGroups  = MediatorLiveData<List<UserGroup>>()

    private var STUserLoginResLiveData  = MediatorLiveData<STUserLoginRes>()

    init {
        STUserLoginResLiveData.value = STUserLoginRes()
    }



    fun login(userName: String,password: String, shouldFetch: Boolean = true, callDelay: Long = 0) : LiveData<Resource<STUserLoginRes>> {
        return object : NetworkBoundResource<STUserLoginRes, STUserLoginRes>(appExecutors) {

            override fun saveCallResult(item: STUserLoginRes) {
                Thread(Runnable { STUserLoginResLiveData.postValue(item) }).start()
            }

            override fun shouldFetch(data: STUserLoginRes?): Boolean {
                return data == null || shouldFetch
            }

            override fun fetchDelayMillis(): Long {
                return callDelay
            }

            override fun loadFromDb() : LiveData<STUserLoginRes> {
                return STUserLoginResLiveData
            }

            override fun createCall(): LiveData<ApiResponse<STUserLoginRes>> =
                webservice.login(userName,password)

        }.asLiveData()
    }



//    fun changePassword(userName: String,password: String,newPassword: String,fromIP: String, token: String,deviceInfo : String,signature: String, shouldFetch: Boolean = true, callDelay: Long = 0) :  LiveData<Resource<MyCTSVCap>> {
//        return object : NetworkBoundResource<MyCTSVCap, CTSVAssignUserActivityByEmailRes>(appExecutors) {
//
//            override fun saveCallResult(item: CTSVAssignUserActivityByEmailRes) {
//
//            }
//
//            override fun shouldFetch(data: MyCTSVCap?): Boolean {
//                return data == null || shouldFetch
//            }
//
//            override fun fetchDelayMillis(): Long {
//                return callDelay
//            }
//
//            override fun loadFromDb() : LiveData<MyCTSVCap> {
//                return ctsvCapLiveData
//            }
//
//            override fun createCall(): LiveData<ApiResponse<CTSVAssignUserActivityByEmailRes>> =
//                webservice.changePassword(userName,password,newPassword,fromIP,token,deviceInfo,signature)
//
//        }.asLiveData()
//    }
//
//    fun lostPassword(userName: String,deviceInfo : String,signature: String, shouldFetch: Boolean = true, callDelay: Long = 0) :  LiveData<Resource<MyCTSVCap>> {
//        return object : NetworkBoundResource<MyCTSVCap, CTSVAssignUserActivityByEmailRes>(appExecutors) {
//
//            override fun saveCallResult(item: CTSVAssignUserActivityByEmailRes) {
//
//            }
//
//            override fun shouldFetch(data: MyCTSVCap?): Boolean {
//                return data == null || shouldFetch
//            }
//
//            override fun fetchDelayMillis(): Long {
//                return callDelay
//            }
//
//            override fun loadFromDb() : LiveData<MyCTSVCap> {
//                return ctsvCapLiveData
//            }
//
//            override fun createCall(): LiveData<ApiResponse<CTSVAssignUserActivityByEmailRes>> =
//                webservice.lostPassword(userName,deviceInfo,signature)
//
//        }.asLiveData()
//    }
//
//    fun checkOTPLostPassword(userName: String,deviceInfo : String,otp: String,signature: String, shouldFetch: Boolean = true, callDelay: Long = 0) :  LiveData<Resource<MyCTSVCap>> {
//        return object : NetworkBoundResource<MyCTSVCap, CTSVAssignUserActivityByEmailRes>(appExecutors) {
//
//            override fun saveCallResult(item: CTSVAssignUserActivityByEmailRes) {
//            }
//
//            override fun shouldFetch(data: MyCTSVCap?): Boolean {
//                return data == null || shouldFetch
//            }
//
//            override fun fetchDelayMillis(): Long {
//                return callDelay
//            }
//
//            override fun loadFromDb() : LiveData<MyCTSVCap> {
//                return ctsvCapLiveData
//            }
//
//            override fun createCall(): LiveData<ApiResponse<CTSVAssignUserActivityByEmailRes>> =
//                webservice.checkOTPLostPassword(userName,deviceInfo,otp,signature)
//
//        }.asLiveData()
//    }

    private fun insertAllToRoom(nppGuestInfos: List<NPPGuestInfo>) {
        runOnIoThread {
            userDAO.insertAll(nppGuestInfos)
        }
    }

    fun insertToRoom(nppGuestInfo: NPPGuestInfo) {
        runOnIoThread {
            userDAO.insert(nppGuestInfo)
        }
    }

    fun updateToRoom(nppGuestInfo: NPPGuestInfo){
        runOnIoThread {
            userDAO.update(nppGuestInfo)
        }
    }

    fun deleteFromRoom(nppGuestInfo: NPPGuestInfo) {
        runOnIoThread {
            userDAO.delete(nppGuestInfo)
        }
    }


}