package com.finnomena.project.candidate.monsterdetail.services

import com.finnomena.project.candidate.monsterdetail.model.MonsterDetailModel
import retrofit2.Call
import retrofit2.http.*

interface MonsterDetailApi {

    @GET("")
    fun getMonsterDetail(
        @Url url: String
    ): Call<MonsterDetailModel>

}