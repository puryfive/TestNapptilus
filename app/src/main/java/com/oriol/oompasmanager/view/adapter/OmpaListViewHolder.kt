package com.oriol.oompasmanager.view.adapter

import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.oriol.oompasmanager.BR
import com.oriol.oompasmanager.R
import com.oriol.oompasmanager.model.entities.ResultsItem
import org.jetbrains.anko.sdk27.coroutines.onClick


class OmpaListViewHolder constructor(private val dataBinding: ViewDataBinding)
    : RecyclerView.ViewHolder(dataBinding.root) {

    fun setup(itemData: ResultsItem) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()

        itemView.onClick {
            val bundle = bundleOf("id" to itemData.id)
            itemView.findNavController().navigate(R.id.action_ompaListFragment_to_ompaDetailFragment, bundle)
        }
    }
}