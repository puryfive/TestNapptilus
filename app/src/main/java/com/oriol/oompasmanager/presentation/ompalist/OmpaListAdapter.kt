package com.oriol.oompasmanager.presentation.ompalist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.oriol.oompasmanager.domain.model.ResultsItem
import com.oriol.oompasmanager.utils.FilterBy
import com.oriol.oompasmanager.utils.Orders

class OmpaListAdapter : RecyclerView.Adapter<OmpaListViewHolder>(), Filterable {
    var ompaList: List<ResultsItem?> = emptyList()
    var ompaInitialList: List<ResultsItem?> = emptyList()
    var ompaListSearchList: List<ResultsItem?> = emptyList()

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
        this.ompaListSearchList = ompaList
        this.ompaInitialList = ompaList
        notifyDataSetChanged()
    }

    //Unused feature uncomment if is need it
    fun orderList(order: Orders) {
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

    fun filterList(filterBy: FilterBy) {
        if (!filterBy.equals(FilterBy.MF)) {
            this.ompaList = this.ompaInitialList.filter { s ->
                s?.gender == filterBy.name
            }
        }
        else{
            this.ompaList = this.ompaInitialList
        }
        this.ompaListSearchList = this.ompaList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    ompaList = ompaListSearchList
                } else {
                    val filteredList = ArrayList<ResultsItem>()
                    for (row in ompaListSearchList) {
                        if (row?.profession!!.toLowerCase().startsWith(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    ompaList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = ompaList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                ompaList = filterResults.values as ArrayList<ResultsItem>
                notifyDataSetChanged()
            }
        }
    }
}