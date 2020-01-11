package com.example.avtovokzal.adverts

import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avtovokzal.databinding.FragmentAdvertsBinding
import com.example.avtovokzal.findAdvert.AdvertsViewModel
import com.example.avtovokzal.postAdvert.AdvertModelPresenterLevel
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*


fun AdvertsFragment.initRecyclerView(
    advertsViewModel: AdvertsViewModel,
    viewDataBinding: FragmentAdvertsBinding
) {
    val viewModel = viewDataBinding.viewmodel
    val adapter = AdvertsAdapter(viewModel as AdvertsViewModel)
    viewDataBinding.recyclerView.apply {
        this.adapter = adapter
        layoutManager = LinearLayoutManager(requireContext())
        addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        setHasFixedSize(true)
    }
    advertsViewModel.items.observe(this, Observer {
        it?.let {
            adapter.submitList(it)
        }
    })
    viewDataBinding.lifecycleOwner = this
}

fun AdvertsFragment.changeOnBackPressed(advertsFragment: AdvertsFragment) {
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
    with(advertsViewModel.advertsLoadedEvent.value?.peekContent()?.first()) {
        requireActivity().toolbar?.title = "${this?.fromCity} - ${this?.toCity}"
    }
}

suspend fun tripleNullCheck(
    advertModel: AdvertModelPresenterLevel,
    action:suspend (fromCity: String, toCity: String, date: Date) -> Unit
) {
    with(advertModel) {
        fromCity.value?.let { fromCityV ->
            toCity.value?.let { toCityV ->
                date.value?.let { dateV ->
                    action(fromCityV, toCityV, dateV)
                }
            }
        }
    }
}