package com.example.avtovokzal.adverts

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avtovokzal.findAdvert.SlideshowViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_adverts.*

fun AdvertsFragment.initRecyclerView(slideShowViewModel: SlideshowViewModel) {
    recycler_view.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
        adapter = AdvertsAdapter(slideShowViewModel)
    }
}

fun AdvertsFragment.changeOnBackPressed(advertsFragment:AdvertsFragment) {
    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() { // Handle the back button event
                NavHostFragment.findNavController(advertsFragment)
                    .navigate(AdvertsFragmentDirections.actionAdvertsFragment2ToNavHome())
            }
        }
    requireActivity().onBackPressedDispatcher.addCallback(this, callback)
}

fun AdvertsFragment.changeTitle() {
    with(slideShowViewModel.advertsLoadedEvent.value?.peekContent()?.first()) {
        requireActivity().toolbar?.title = "${this?.fromCity} - ${this?.toCity}"
    }
}