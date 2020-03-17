package com.finnomena.project.candidate.monsterdetail.model

import com.google.gson.annotations.SerializedName

data class MonsterDetailModel(
    @SerializedName("name")         var name: String = "",
    @SerializedName("height")       var height: Int = 0,
    @SerializedName("weight")       var weight: Int = 0,
    @SerializedName("sprites")      var sprites: Sprites? = null,
    @SerializedName("types")        var types: ArrayList<Types>?  = null
) {
    data class Sprites (
        @SerializedName("front_default")    var frontDefault: String? = null
    )
    data class Types (
        @SerializedName("slot")     var slot: Int = 0,
        @SerializedName("type")     var type: Type? = null
    ) {
        data class Type (
            @SerializedName("name")    var name: String = "",
            @SerializedName("url")     var url: String = ""
        )
    }
}