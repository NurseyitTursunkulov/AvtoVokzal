package com.example.avtovokzal.adverts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avtovokzal.R
import com.example.avtovokzal.databinding.FragmentAdvertsBinding
import com.example.avtovokzal.findAdvert.AdvertsViewModel
import com.example.avtovokzal.findAdvert.FindAdvertsFragmentDirections
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AdvertsFragment : Fragment() {
    val advertsViewModel: AdvertsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewDataBinding: FragmentAdvertsBinding =
            inflate<FragmentAdvertsBinding>(
                inflater, R.layout.fragment_adverts, container,
                false
            ).apply {
                viewmodel = advertsViewModel
            }
        initRecyclerView(advertsViewModel, viewDataBinding)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeTitle()
        changeOnBackPressed(this@AdvertsFragment)

        advertsViewModel.advertClickedEvent.observe(viewLifecycleOwner,Observer{
            this.findNavController()
                .navigate(AdvertsFragmentDirections.actionAdvertsFragment2ToAdvertDetailFragment())
        })
    }
}