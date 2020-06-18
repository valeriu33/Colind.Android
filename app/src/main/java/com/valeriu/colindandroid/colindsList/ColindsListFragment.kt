package com.valeriu.colindandroid.colindsList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.valeriu.colindandroid.EventObserver
import com.valeriu.colindandroid.databinding.FragmentColindsListBinding
import com.valeriu.colindandroid.utils.getViewModelFactory

class ColindsListFragment : Fragment() {

    private val viewModel by viewModels<ColindsListViewModel> { getViewModelFactory() }

    private lateinit var viewDataBinding: FragmentColindsListBinding

    private lateinit var listAdapter: ColindsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentColindsListBinding.inflate(inflater, container, false)
        viewDataBinding.viewModel = this@ColindsListFragment.viewModel

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        setupListAdapter()
//        setupRefreshLayout(viewDataBinding.refreshLayout, viewDataBinding.tasksList)
//        viewDataBinding.r.scrollUpChild = scrollUpChild
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.openColindEvent.observe(this, EventObserver {
            openColindDetails(it)
        })
    }

    private fun openColindDetails(colindId: Int) {
        val action = ColindsListFragmentDirections.actionColindsListFragmentToColindDetailFragment(colindId)
        findNavController().navigate(action)
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewModel
        if (viewModel != null) {
            listAdapter = ColindsListAdapter(viewModel)
            viewDataBinding.recyclerListColinds.adapter = listAdapter
        } else {
            Log.v("Error","ViewModel not initialized when attempting to set up adapter.")// TODO Snack fucking bar
        }
    }
}
