package com.oriol.oompasmanager.presentation.ompalist

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
    var spinner: Spinner? = null

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
        viewDataBinding.viewmodel?.fetchOmpaList(1)

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.ompaListLive?.observe(viewLifecycleOwner, Observer {
            it.results?.let { it1 -> adapter.updateOmpaList(it1) }
            it.total?.let { it1 -> if(spinner?.adapter?.isEmpty() == true || spinner?.adapter?.isEmpty() == null) setupSpinner(it1) }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val item = menu.findItem(R.id.spinner_page)
        spinner = item.getActionView() as Spinner
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupSpinner(total:Int){
        val pageList = Array(total) { getString(R.string.page) + " ${it.plus(1)}" }
        val adapter = ArrayAdapter(this.context, android.R.layout.simple_spinner_item, pageList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = adapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewDataBinding.viewmodel?.fetchOmpaList(position + 1)
            }
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