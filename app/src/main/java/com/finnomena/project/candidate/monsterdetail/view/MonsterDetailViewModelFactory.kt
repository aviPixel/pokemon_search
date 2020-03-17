package com.finnomena.project.candidate.monsterdetail.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finnomena.project.candidate.monsterdetail.interfaces.IMonsterDetail
import com.finnomena.project.candidate.monsterdetail.viewmodel.MonsterDetailViewModel

@Suppress("UNCHECKED_CAST")
class MonsterDetailViewModelFactory(private val act: IMonsterDetail.View) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MonsterDetailViewModel(act) as T
    }

}