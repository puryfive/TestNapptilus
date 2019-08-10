package com.oriol.oompasmanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oriol.oompasmanager.model.entities.ResultsItem
import com.oriol.oompasmanager.utils.Orders
import com.oriol.oompasmanager.view.ui.OmpaListViewModel

class OmpaListAdapter : RecyclerView.Adapter<OmpaListViewHolder>() {
    var ompaList: List<ResultsItem?> = emptyList()
    lateinit var ompasOrder: Orders

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OmpaListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = com.oriol.oompasmanager.databinding.ViewOmpasListItemBinding.inflate(inflater, parent, false)
        return OmpaListViewHolder(dataBinding)
    }

    override fun getItemCount() = ompaList.size

    override fun onBindViewHolder(holder: OmpaListViewHolder, position: Int) {
        ompaList[position]?.let { holder.setup(it) }
    }

    fun updateOmpaList(ompaList: List<ResultsItem?>) {
        this.ompaList = ompaList
        notifyDataSetChanged()
    }

    fun orderList(order: Orders) {
        ompasOrder = order
        when (order) {
            Orders.GENDER ->
                if (this.ompaList.equals(this.ompaList.sortedBy { it?.gender })) {
                    this.ompaList = this.ompaList.sortedByDescending {
                        it?.gender
                    }
                } else {
                    this.ompaList = this.ompaList.sortedBy {
                        it?.gender
                    }
                }


            Orders.PROFESSION ->
                if (this.ompaList.equals(this.ompaList.sortedBy { it?.profession })){
                    this.ompaList = this.ompaList.sortedByDescending {
                        it?.profession
                    }
                } else {
                    this.ompaList = this.ompaList.sortedBy {
                        it?.profession
                    }
                }

            Orders.GENDERANDPROFESSION ->
                if (this.ompaList.equals(this.ompaList.sortedWith(compareBy({ it?.gender }, { it?.profession })))){
                    this.ompaList = this.ompaList.sortedWith(compareBy({ it?.gender }, { it?.profession })).reversed()
                }
                else{
                    this.ompaList = this.ompaList.sortedWith(compareBy({ it?.gender }, { it?.profession }))
                }
        }
        notifyDataSetChanged()
    }
}