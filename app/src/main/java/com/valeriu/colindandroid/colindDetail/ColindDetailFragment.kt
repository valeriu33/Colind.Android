package com.valeriu.colindandroid.colindDetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.valeriu.colindandroid.R
import com.valeriu.colindandroid.databinding.FragmentColindDetailBinding
import com.valeriu.colindandroid.utils.getViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class ColindDetailFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentColindDetailBinding

    private val args: ColindDetailFragmentArgs by navArgs()

    private val viewModel by viewModels<ColindDetailViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_colind_detail, container, false)
        viewDataBinding = FragmentColindDetailBinding.bind(view)
        viewDataBinding.viewModel = this@ColindDetailFragment.viewModel

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.start(args.colindId)
        return view
    }
}
