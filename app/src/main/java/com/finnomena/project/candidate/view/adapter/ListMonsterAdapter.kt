package com.finnomena.project.candidate.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.databinding.ViewholderEmptyTopBinding
import com.finnomena.project.candidate.databinding.ViewholderItemBinding
import com.finnomena.project.candidate.model.ListMonsterModel
import com.finnomena.project.core.appmanagement.ViewHolderEmptyTop
import com.finnomena.project.core.utils.GlideLib
import kotlin.collections.ArrayList

class ListMonsterAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_EMPTY = 0
    private val VIEW_ITEM = 1

    private lateinit var mContext: Context
    private lateinit var mListener: ListMonsterAdapter.OnItemClickListener

    private lateinit var mListData: ArrayList<ListMonsterModel.Results>

    interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    constructor(context: Context, listener: OnItemClickListener) : this() {
        this.mContext = context
        this.mListener = listener
        mListData = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_ITEM -> {
                val binding = DataBindingUtil.inflate<ViewholderItemBinding>(LayoutInflater.from(parent.context), R.layout.viewholder_item, parent,false)
                ItemViewHolder(binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<ViewholderEmptyTopBinding>(LayoutInflater.from(parent.context), R.layout.viewholder_empty_top, parent,false)
                ViewHolderEmptyTop(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind()
        } else if (holder is ViewHolderEmptyTop) {
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return mListData.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_EMPTY
        }
        return VIEW_ITEM
    }

    fun setDataItem(data: ArrayList<ListMonsterModel.Results>) {
        mListData = data
        notifyDataSetChanged()
    }

    fun setSearchFilter(dataFilter: ArrayList<ListMonsterModel.Results>) {
        mListData = ArrayList()
        mListData.addAll(dataFilter)
        notifyDataSetChanged()
    }

    fun clearList() {
        mListData.clear()
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(binding: ViewholderItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var holder: ViewholderItemBinding? = DataBindingUtil.bind(itemView)

        fun bind() {
            holder?.executePendingBindings()

            val indexItem = adapterPosition - 1
            val item = mListData[indexItem]

            holder?.tvName?.text = item.name
            holder?.ivMonster?.let {
                GlideLib.setImage(mContext, "https://pokeres.bastionbot.org/images/pokemon/${(item.id?.plus(1))}.png", it)
            }

        }

    }

}