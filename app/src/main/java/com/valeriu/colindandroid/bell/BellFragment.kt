package com.valeriu.colindandroid.bell

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.valeriu.colindandroid.R
import com.valeriu.colindandroid.colindsList.ColindsActivity
import com.valeriu.colindandroid.databinding.FragmentColindDetailBinding
import com.valeriu.colindandroid.utils.getViewModelFactory

class BellFragment : Fragment() {

//    private lateinit var viewDataBinding: FragmentBellBinding

    private val viewModel by viewModels<BellViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as ColindsActivity?)?.supportActionBar?.title = ""
        return inflater.inflate(R.layout.bell_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}