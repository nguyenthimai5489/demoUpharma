//package com.example.upharma.repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MediatorLiveData
//import com.example.upharma.common.ApiResponse
//import com.example.upharma.common.AppExecutors
//import com.example.upharma.common.NetworkBoundResource
//import com.example.upharma.common.Resource
//import com.example.upharma.model.MyUpharmaCap
//import com.example.upharma.model.UpharmaCap
//import com.example.upharma.model.entity.GuestInfo
//import com.example.upharma.model.res.guest.NPPCreateGuestInfoRes
//import com.example.upharma.utilities.runOnIoThread
//import com.example.upharma.webservice.WebService
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class GuestRepository @Inject constructor(
//
//    private val webservice: WebService,
//    private val appExecutors: AppExecutors
//) {
//
//    private var NPPCreateGuestInfoResLiveData = MediatorLiveData<MyUpharmaCap>()
//
//    init {
//                NPPCreateGuestInfoResLiveData.value = MyUpharmaCap()
//    }
//
//
//    fun createGuestInfo(
//        userName: String,
//        password: String,
//        address: String,
//        shouldFetch: Boolean = true,
//        callDelay: Long = 0
//    ): LiveData<Resource<GuestInfo>> {
//        return object : NetworkBoundResource<GuestInfo, NPPCreateGuestInfoRes>(appExecutors) {
//
//            override fun saveCallResult(item: NPPCreateGuestInfoRes) {
//
//            }
//
//            override fun shouldFetch(data: GuestInfo?): Boolean {
//                return data == null || shouldFetch
//            }
//
//            override fun fetchDelayMillis(): Long {
//                return callDelay
//            }
//
//            override fun loadFromDb(): LiveData<GuestInfo> {
//                return MyUpharmaCap()
//            }
//
//            override fun createCall(): LiveData<ApiResponse<NPPCreateGuestInfoRes>> =
//                webservice.createGuestInfo(userName, password, address)
//
//        }.asLiveData()
//    }
//
//}