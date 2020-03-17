package com.finnomena.project.core.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiCallback<T>: Callback<T> {

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        if (response == null) {
            fail(response?.code().toString(), "null")
        } else {
            if (response.isSuccessful && response.body() != null && response.code() == 200) {
                success(response.body()!!)
            } else {
                fail(response.code().toString(), response.message())
            }
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        if (t == null) {
            fail("", "null")
        } else {
            if (t.message != null && t.message!!.contains("Unable to resolve host")) {
                fail("500", "Internet problem")
            } else {
                fail(t.hashCode().toString(), t.message ?: "")
            }
        }
    }

    abstract fun success(data: T)
    abstract fun fail(code: String, message: String)

}