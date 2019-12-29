package com.example.avtovokzal.adverts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.avtovokzal.R
import com.example.avtovokzal.findAdvert.SlideshowViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AdvertsFragment : Fragment() {
    val slideShowViewModel:SlideshowViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adverts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(slideShowViewModel)
        changeTitle()
        changeOnBackPressed(this@AdvertsFragment)
    }
}