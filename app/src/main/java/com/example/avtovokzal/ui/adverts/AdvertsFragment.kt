package com.example.avtovokzal.ui.adverts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.avtovokzal.R
import com.example.avtovokzal.ui.home.HomeFragmentDirections
import com.example.avtovokzal.ui.slideshow.SlideshowViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_adverts.*
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