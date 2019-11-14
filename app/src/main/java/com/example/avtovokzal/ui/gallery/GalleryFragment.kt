package com.example.avtovokzal.ui.gallery

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.avtovokzal.ui.gallery.util.selectTime
import com.example.avtovokzal.R as T
import kotlinx.android.synthetic.main.fragment_gallery.*


class GalleryFragment : Fragment() {

    internal lateinit var galleryViewModel: GalleryViewModel
    var languagesA =
        arrayOf(
            "Исфана",
            "Кадамжай",
            "Баткен",
            "Aксы",
            "Ала-Бука",
            "Бишкек",
            "Ош",
            "Жалал-Абад",
            "JSON"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        return inflater.inflate(T.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set the number of characters the user must type before the drop down list is shown
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), R.layout.select_dialog_singlechoice, languagesA)
        fromTV.threshold = 1;
        fromTV.setAdapter(adapter)
        toTV.threshold = 1
        toTV.setAdapter(adapter)
        calendarBtn.setOnClickListener {
            selectTime();
        }
        location_button.setOnClickListener {
            NavHostFragment.findNavController(this@GalleryFragment)
                .navigate(GalleryFragmentDirections.actionNavGalleryToMapsFragment())
        }
    }


}
