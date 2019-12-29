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
import com.example.avtovokzal.databinding.FragmentSlideshowBinding
import com.example.avtovokzal.postAdvert.util.selectTime
import com.example.avtovokzal.util.EventObserver
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_slideshow.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import com.example.avtovokzal.R as T

class SlideshowFragment : Fragment() {

    private val slideshowViewModel: SlideshowViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = inflate<FragmentSlideshowBinding>(inflater, T.layout.fragment_slideshow, container, false)
        binding.viewModel = slideshowViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarBtn.setOnClickListener {
            selectTime(
                ontimeSelected = { year: Int, month: Int, day: Int, hour: Int, min: Int ->
                    slideshowViewModel.onTimeSelected(year,month,day,hour,min)
                }
            )
        }

        slideshowViewModel.dialog.observe(this,EventObserver{
            showSuccessDialog(it);
        })
        slideshowViewModel.spinner.observe(this,Observer{state:Boolean->
            showProgressBar(state)
        })
        slideshowViewModel.advertModel.date.observe(this, Observer {
            showSelectedDate(it)
        })
        slideshowViewModel.advertModel.address.observe(this, EventObserver {
            showAdress(it)
        })
        slideshowViewModel.snackBar.observe(this,EventObserver{
            Snackbar.make(fromTV, it, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        })
        slideshowViewModel.cities.observe(this, Observer {
            initAutoCompleteTextViewForCities( R.layout.select_dialog_singlechoice, it)
        })
        slideshowViewModel.advertsLoadedEvent.observe(this,EventObserver{
            this.findNavController()
                .navigate(SlideshowFragmentDirections.actionNavSlideshowToAdvertsFragment2())
        })
    }
}
