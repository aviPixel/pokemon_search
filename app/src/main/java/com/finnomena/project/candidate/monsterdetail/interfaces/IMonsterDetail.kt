package com.finnomena.project.candidate.monsterdetail.interfaces


interface IMonsterDetail {

    interface View {

        fun initView()

        fun initObserv()

        fun apiGetMonsterDetailSuccess()

        fun apiGetMonsterDetailFail(code: String, message: String)

    }

    interface Api {

        fun apiMonsterDetail()

    }

}