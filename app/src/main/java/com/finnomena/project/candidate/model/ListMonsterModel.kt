package com.finnomena.project.candidate.model

import com.google.gson.annotations.SerializedName

data class ListMonsterModel (

    @SerializedName("results")      var results: ArrayList<Results>?  = null

) {
    data class Results (

        @SerializedName("id")       var id: Int? = null,
        @SerializedName("name")     var name: String = "",
        @SerializedName("url")      var url: String? = null

    )
}