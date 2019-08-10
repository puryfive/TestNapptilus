package com.oriol.oompasmanager.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oriol.oompasmanager.R
import com.oriol.oompasmanager.databinding.FragmentOmpaListBinding
import com.oriol.oompasmanager.utils.Orders
import com.oriol.oompasmanager.view.adapter.OmpaListAdapter
import kotlinx.android.synthetic.main.fragment_ompa_list.*

class OmpaListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentOmpaListBinding
    private lateinit var adapter: OmpaListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentOmpaListBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@OmpaListFragment).get(OmpaListViewModel::class.java)
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
        R.id.sortGenderBtn -> {
            orderList(Orders.GENDER)
            true
        }

        R.id.sortProfessionBtn -> {
            orderList(Orders.PROFESSION)
            true
        }

        R.id.sortProfessionAndGenderBtn -> {
            orderList(Orders.GENDERANDPROFESSION)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun orderList(order: Orders){
        adapter.orderList(order)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(true)
    }
}