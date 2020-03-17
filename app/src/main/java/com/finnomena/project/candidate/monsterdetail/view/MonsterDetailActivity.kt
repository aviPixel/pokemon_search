package com.finnomena.project.candidate.monsterdetail.view

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.monsterdetail.interfaces.IMonsterDetail
import com.finnomena.project.candidate.monsterdetail.viewmodel.MonsterDetailViewModel
import com.finnomena.project.core.utils.GlideLib
import com.finnomena.project.core.utils.setStatusBarWhite
import com.finnomena.project.core.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_monster_deail.*
import kotlinx.android.synthetic.main.view_frame_back.*

class MonsterDetailActivity : AppCompatActivity(), IMonsterDetail.View, IMonsterDetail.Api {

    private val viewModel by lazy {
        ViewModelProviders.of(this, MonsterDetailViewModelFactory(this)).get((MonsterDetailViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monster_deail)

        initView()

        initObserv()

        apiMonsterDetail()
    }

    override fun initView() {
        setStatusBarWhite()

        frameBack?.setOnClickListener { finish() }
    }

    override fun initObserv() {
        viewModel.getMonsterDetail().observe(this, Observer { data ->
            data.sprites?.frontDefault?.let { img ->
                GlideLib.setImage(this, img, ivMonster)
            }

            tvName.text = data.name
            tvHeight.text = data.height.toString()
            tvWeight.text = data.weight.toString()


            data.types?.let { classMonster ->
                val frameOpenCloseTime = findViewById<LinearLayout>(R.id.frameTypeMonster)
                frameOpenCloseTime.orientation = LinearLayout.VERTICAL

                for (i in classMonster.indices) {
                    val tv = TextView(this@MonsterDetailActivity)
                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                    tv.layoutParams = lp
                    tv.setTextAppearance(this, R.style.appTextDetail)
                    tv.text = classMonster[i].type?.name

                    frameOpenCloseTime.addView(tv)
                }
            }

        })
    }

    override fun apiGetMonsterDetailSuccess() {
        frameDetail?.visible()
    }

    override fun apiGetMonsterDetailFail(code: String, message: String) {
        Toast.makeText(this, "$code $message", Toast.LENGTH_SHORT).show()
    }

    override fun apiMonsterDetail() {
        val bundle = intent.getStringExtra("url")
        bundle?.let {url ->
            viewModel.apiGetMonsterDetail(url)
        }
    }

}