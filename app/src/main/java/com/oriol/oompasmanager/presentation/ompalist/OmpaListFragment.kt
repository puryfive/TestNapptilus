package com.oriol.oompasmanager.presentation.ompalist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oriol.oompasmanager.R
import com.oriol.oompasmanager.databinding.FragmentOmpaListBinding
import com.oriol.oompasmanager.utils.FilterBy
import kotlinx.android.synthetic.main.fragment_ompa_list.*
import org.koin.android.ext.android.inject

class OmpaListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentOmpaListBinding
    lateinit var adapter: OmpaListAdapter

    val ompaListViewModel : OmpaListViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentOmpaListBinding.inflate(inflater, container, false).apply {
            viewmodel = ompaListViewModel
            setLifecycleOwner(viewLifecycleOwner)
        }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchOmpaList()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.ompaListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateOmpaList(it)
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = OmpaListAdapter()
            val layoutManager = LinearLayoutManager(activity)
            ompa_list_rv.layoutManager = layoutManager
            ompa_list_rv.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            ompa_list_rv.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filterByFemale -> {
            filterList(FilterBy.F)
            true
        }

        R.id.filterByMale -> {
            filterList(FilterBy.M)
            true
        }

        R.id.filterByMaleAndFemale -> {
            filterList(FilterBy.MF)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun filterList(filterBy: FilterBy){
        adapter.filterList(filterBy)
    }
}