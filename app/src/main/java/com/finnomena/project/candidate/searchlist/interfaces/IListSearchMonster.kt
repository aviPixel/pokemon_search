package com.finnomena.project.candidate.searchlist.interfaces


interface IListSearchMonster {

    interface view {

        fun initView()

        fun initObserv()

        fun initSearch()

        fun setListMonster()

        fun showLoading()

        fun hideLoading()

        fun disableAllView()

        fun enableAllView()

        fun apiGetListMonsterSuccess()

        fun apiGetListMonsterFail(code: String, message: String)

    }

    interface api {

        fun apiGetListMonster()

    }

    interface navigation {

        fun goToMonsterDetail(url: String)

    }

}