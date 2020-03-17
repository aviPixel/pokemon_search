package com.finnomena.project.candidate.monsterdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finnomena.project.candidate.monsterdetail.interfaces.IMonsterDetail
import com.finnomena.project.candidate.monsterdetail.model.MonsterDetailModel
import com.finnomena.project.candidate.monsterdetail.services.MonsterDetailApi
import com.finnomena.project.core.network.ApiCallback
import com.finnomena.project.core.network.ApiService

class MonsterDetailViewModel(private val act: IMonsterDetail.View) : ViewModel() {

    private var monsterDetail = MutableLiveData<MonsterDetailModel>()

    fun apiGetMonsterDetail(url: String) {
        ApiService.initEndPoint().create(MonsterDetailApi::class.java)
            .getMonsterDetail(url).enqueue(object : ApiCallback<MonsterDetailModel>() {
                override fun success(data: MonsterDetailModel) {
                    monsterDetail.value = data
                    act.apiGetMonsterDetailSuccess()
                }

                override fun fail(code: String, message: String) {
                    act.apiGetMonsterDetailFail(code, message)
                }
            })
    }

    fun getMonsterDetail(): LiveData<MonsterDetailModel> {
        return monsterDetail
    }

}