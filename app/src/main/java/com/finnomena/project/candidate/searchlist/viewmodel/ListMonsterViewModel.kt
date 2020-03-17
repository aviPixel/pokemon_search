package com.finnomena.project.candidate.searchlist.viewmodel

import androidx.lifecycle.ViewModel
import com.finnomena.project.candidate.searchlist.interfaces.IListSearchMonster
import com.finnomena.project.candidate.searchlist.model.ListMonsterModel
import com.finnomena.project.candidate.searchlist.services.ListSearchMonsterApi
import com.finnomena.project.core.network.ApiCallback
import com.finnomena.project.core.network.ApiService

class ListMonsterViewModel(private val act: IListSearchMonster.view) : ViewModel() {

    private var listMonsterAll = ArrayList<ListMonsterModel.Results>()

    fun apiGetListMonster() {
        ApiService.initEndPoint().create(ListSearchMonsterApi::class.java)
            .getListMonster().enqueue(object : ApiCallback<ListMonsterModel>() {
                override fun success(data: ListMonsterModel) {
                    data.results?.let {
                        for (i in it.indices) {
                            it[i].id = i
                            listMonsterAll.add(it[i])
                        }

                        act.setListMonster()
                    }
                }

                override fun fail(code: String, message: String) {
                    act.apiGetListMonsterFail(code, message)
                }
            })
    }

    fun getListMonster(): ArrayList<ListMonsterModel.Results> {
        return listMonsterAll
    }

    fun getListFilter(dataFilter: String): ArrayList<ListMonsterModel.Results>? {
        val listMonsterFilter = ArrayList<ListMonsterModel.Results>()
        listMonsterAll?.let { data ->
            for (i in data.indices) {
                if (data[i].name.toLowerCase().contains(dataFilter.toLowerCase())) {
                    data[i].id = i
                    listMonsterFilter.add(data[i])
                }
            }
        }
        return listMonsterFilter
    }

}