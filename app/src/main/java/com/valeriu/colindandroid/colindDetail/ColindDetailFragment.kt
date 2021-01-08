package com.valeriu.colindandroid.colindDetail


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.valeriu.colindandroid.EventObserver
import com.valeriu.colindandroid.R
import com.valeriu.colindandroid.colindsList.ColindsActivity
import com.valeriu.colindandroid.databinding.FragmentColindDetailBinding
import com.valeriu.colindandroid.utils.getViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class ColindDetailFragment : Fragment() {

    private val viewModel by viewModels<ColindDetailViewModel> { getViewModelFactory() }

    private lateinit var viewDataBinding: FragmentColindDetailBinding

    private val args: ColindDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_colind_detail, container, false)
        viewDataBinding = FragmentColindDetailBinding.bind(view)
        viewDataBinding.viewModel = this@ColindDetailFragment.viewModel

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        val actionBar = (activity as ColindsActivity?)?.supportActionBar
        (activity as ColindsActivity?)?.supportActionBar?.title = ""
        viewModel.openColindEvent.observe(this, EventObserver {
            actionBar?.title = it
        })

        viewModel.start(args.colindId)

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.removeItem(R.id.action_search)

        super.onCreateOptionsMenu(menu, inflater)
    }
}
