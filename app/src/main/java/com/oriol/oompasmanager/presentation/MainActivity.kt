package com.oriol.oompasmanager.presentation

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.oriol.oompasmanager.R
import com.oriol.oompasmanager.presentation.ompalist.OmpaListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.main_nav_fragment))
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_fragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView
        searchView!!.setSearchableInfo(searchManager
            .getSearchableInfo(componentName))
        searchView!!.maxWidth = Integer.MAX_VALUE
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                findOmpaListFragment().adapter!!.filter.filter(query)
                return false
            }
            override fun onQueryTextChange(query: String): Boolean {
                findOmpaListFragment().adapter.filter.filter(query)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun findOmpaListFragment() : OmpaListFragment{
        lateinit var listFragment: OmpaListFragment
        (supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment?)?.let {
            listFragment = it.childFragmentManager.fragments.filter {
                it is OmpaListFragment
            }.single() as OmpaListFragment}
        return listFragment
    }
}
