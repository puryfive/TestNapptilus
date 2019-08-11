package com.oriol.oompasmanager.presentation.ompadetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.oriol.oompasmanager.R
import com.oriol.oompasmanager.databinding.FragmentOmpaDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ompa_detail.*
import org.koin.android.ext.android.inject


class OmpaDetailFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentOmpaDetailBinding

    val ompaListViewModel : OmpaDetailViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentOmpaDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = ompaListViewModel
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        fetchData()
        setupObservers()
    }

    private fun fetchData(){
        val id = arguments?.let { OmpaDetailFragmentArgs.fromBundle(it).id }
        id?.let { viewDataBinding.viewmodel?.fetchOmpaDetail(it) }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.ompaDetailLive?.observe(viewLifecycleOwner, Observer {
            Picasso.get()
                .load(viewDataBinding.viewmodel?.ompaDetailLive?.value?.image)
                .placeholder(R.drawable.choco_bar_placeholder)
                .error(R.drawable.error)
                .fit()
                .into(item_image)
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.filterByFemale).setVisible(false)
        menu.findItem(R.id.filterByMale).setVisible(false)
        menu.findItem(R.id.filterByMaleAndFemale).setVisible(false)
        menu.findItem(R.id.action_search).setVisible(false)
    }
}