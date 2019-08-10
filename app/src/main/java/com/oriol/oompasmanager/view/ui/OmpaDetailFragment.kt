package com.oriol.oompasmanager.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.oriol.oompasmanager.databinding.FragmentOmpaDetailBinding


class OmpaDetailFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentOmpaDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentOmpaDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@OmpaDetailFragment).get(OmpaDetailViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val id = arguments?.let { OmpaDetailFragmentArgs.fromBundle(it).id }

        id?.let { viewDataBinding.viewmodel?.fetchOmpaDetail(it) }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(com.oriol.oompasmanager.R.id.sortProfessionBtn).setVisible(false)
        menu.findItem(com.oriol.oompasmanager.R.id.sortGenderBtn).setVisible(false)
        menu.findItem(com.oriol.oompasmanager.R.id.sortProfessionAndGenderBtn).setVisible(false)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
    }
}