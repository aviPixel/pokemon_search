package com.finnomena.project.candidate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finnomena.project.candidate.interfaces.IListSearchMonster

@Suppress("UNCHECKED_CAST")
class ListMonsterViewModelFactory(private val act: IListSearchMonster.view) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListMonsterViewModel(act) as T
    }

}