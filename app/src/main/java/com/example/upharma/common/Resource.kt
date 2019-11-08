package com.example.upharma.common

data class Resource<out T>(val status: Status, val data: T?, val respText: String?, val respCode: Int?) {
    companion object {
        fun <T> successDb(data: T?): Resource<T> {
            return Resource(Status.SUCCESS_DB, data, null, null)
        }

        fun <T> successNetwork(data: T?): Resource<T> {
            return Resource(Status.SUCCESS_NETWORK, data, null, null)
        }

        fun <T> errorToken(): Resource<T> {
            return Resource(Status.ERROR_TOKEN, null, null, null)
        }

        fun <T> errorDB(respText: String?,respCode : Int?): Resource<T> {
            return Resource(Status.ERROR_DB, null, respText, respCode)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg, null)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }
    }
}