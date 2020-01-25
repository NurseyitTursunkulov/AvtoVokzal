package com.example.avtovokzal.findAdvert

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.avtovokzal.AdvertsViewModel
import com.example.avtovokzal.databinding.FragmentSlideshowBinding
import com.example.avtovokzal.postAdvert.util.selectTime
import com.example.avtovokzal.util.EventObserver
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_slideshow.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.newSingleThreadContext
import org.koin.android.viewmodel.ext.android.sharedViewModel
import com.example.avtovokzal.R as T

class FindAdvertsFragment : Fragment() {

    private val advertsViewModel: AdvertsViewModel by sharedViewModel()
    private val scope = CoroutineScope(newSingleThreadContext("name"))
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = inflate<FragmentSlideshowBinding>(inflater, T.layout.fragment_slideshow, container, false)
        binding.viewModel = advertsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarBtn.setOnClickListener {
            selectTime(
                ontimeSelected = { year: Int, month: Int, day: Int, hour: Int, min: Int ->
                    advertsViewModel.onTimeSelected(year,month,day,hour,min)
                }
            )
        }

        advertsViewModel.dialog.observe(viewLifecycleOwner,EventObserver{
            showSuccessDialog(it);
        })
        advertsViewModel.spinner.observe(viewLifecycleOwner,Observer{ state:Boolean->
            showProgressBar(state)
        })
        advertsViewModel.advertModel.date.observe(viewLifecycleOwner, Observer {
            showSelectedDate(it)
        })
        advertsViewModel.advertModel.address.observe(viewLifecycleOwner, EventObserver {
            showAdress(it)
        })
        advertsViewModel.snackBar.observe(viewLifecycleOwner,EventObserver{
            Snackbar.make(fromTV, it, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        })
        advertsViewModel.cities.observe(viewLifecycleOwner, Observer {
            initAutoCompleteTextViewForCities( R.layout.select_dialog_singlechoice, it)
        })
        advertsViewModel.advertsLoadedEvent.observe(viewLifecycleOwner,EventObserver{
            this.findNavController()
                .navigate(FindAdvertsFragmentDirections.actionNavFindadvertToAdvertsFragment2())
        })
    }
}
