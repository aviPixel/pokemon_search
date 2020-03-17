package com.finnomena.project.core.appmanagement

import androidx.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmptyModel(
        @SerializedName("Empty")    @Expose     var empty: String
) : BaseObservable() {
    constructor() : this("")
}