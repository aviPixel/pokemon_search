package com.finnomena.project.candidate.searchlist.services

import com.finnomena.project.candidate.searchlist.model.ListMonsterModel
import retrofit2.Call
import retrofit2.http.*

interface ListSearchMonsterApi {

    @GET("pokemon?limit=151")
    fun getListMonster(): Call<ListMonsterModel>

}