package com.oriol.oompasmanager.presentation.ompadetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oriol.oompasmanager.databinding.FragmentOmpaDetailBinding
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