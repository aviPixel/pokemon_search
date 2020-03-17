package com.finnomena.project.core.appmanagement

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.finnomena.project.candidate.databinding.ViewholderEmptyTopBinding


class ViewHolderEmptyTop(binding: ViewholderEmptyTopBinding) : RecyclerView.ViewHolder(binding.root) {

    var viewholderEmptyBinding: ViewholderEmptyTopBinding?

    init {
        viewholderEmptyBinding = DataBindingUtil.bind(itemView)
    }

    fun bind() {
        viewholderEmptyBinding!!.executePendingBindings()
    }

}