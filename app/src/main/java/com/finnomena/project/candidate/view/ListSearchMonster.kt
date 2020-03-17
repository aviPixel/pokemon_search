package com.finnomena.project.candidate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.interfaces.IListSearchMonster
import com.finnomena.project.candidate.view.adapter.ListMonsterAdapter
import com.finnomena.project.candidate.viewmodel.ListMonsterViewModel
import com.finnomena.project.candidate.viewmodel.ListMonsterViewModelFactory
import com.finnomena.project.core.utils.checkClickLastTime
import com.finnomena.project.core.utils.hideSoftInputKeyboard
import com.finnomena.project.core.utils.setStatusBarWhite
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class ListSearchMonster : AppCompatActivity(), IListSearchMonster.view, IListSearchMonster.api {

    private var recyclerAdapter: ListMonsterAdapter? = null

    private val viewModel by lazy {
        ViewModelProviders.of(this, ListMonsterViewModelFactory(this)).get((ListMonsterViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        initObserv()

        apiGetListMonster()
    }

    override fun initView() {
        setStatusBarWhite()

        initSearch()

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvListMonster?.layoutManager = layoutManager
        recyclerAdapter = ListMonsterAdapter(this, object : ListMonsterAdapter.OnItemClickListener {
            override fun onItemClick(id: String){}
        })
        rvListMonster?.adapter = recyclerAdapter

        frameBtnSearch.setOnClickListener {
            if (1000.checkClickLastTime()) return@setOnClickListener
            if (edSearch.text.toString().isEmpty() || edSearch.text.toString() == "") return@setOnClickListener
            hideSoftInputKeyboard()

            viewModel.getListFilter(edSearch.text.toString())?.let { dataList ->
                recyclerAdapter?.setSearchFilter(dataList)
            }

        }
    }

    override fun setListMonster() {
        recyclerAdapter?.setDataItem(viewModel.getListMonster())
    }

    override fun initSearch() {
        edSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().isEmpty() || editable.toString() == "") {
                    ivClose?.visibility = View.INVISIBLE
                    viewModel.getListMonster()?.let {
                        recyclerAdapter?.setDataItem(it)
                    }
                } else {
                    ivClose?.visibility = View.VISIBLE
                }
            }
        })

        ivClose.setOnClickListener {
            edSearch.setText("")
        }
    }

    override fun initObserv() {
//        viewModel.getListMonster().observe(this, Observer { data ->
//            data?.let {
//                Log.d("data", Gson().toJson(data))
//                recyclerAdapter?.setDataItem(it)
//            }
//        })
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun disableAllView() {

    }

    override fun enableAllView() {

    }

    override fun apiGetListMonsterSuccess() {
        hideLoading()
    }

    override fun apiGetListMonsterFail(code: String, message: String) {
        hideLoading()
    }

    override fun apiGetListMonster() {
        viewModel.apiGetListMonster()
    }

}