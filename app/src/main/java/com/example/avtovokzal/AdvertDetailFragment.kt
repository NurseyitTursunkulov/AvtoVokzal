package com.example.avtovokzal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Center
import androidx.ui.layout.FlexColumn
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview

class AdvertDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_advert_detail, container, false)
        (fragmentView as ViewGroup).setContent {
            Hello("Jetpack Compose")
        }

        return fragmentView
    }

    @Composable
    fun Hello(name: String) = MaterialTheme {
        FlexColumn {
            inflexible {
                // Item height will be equal content height
            }
            expanded(1F) {
                // occupy whole empty space in the Column
                Center {
                    // Center content
                    Text("Hello $name!") // Text label
                }
            }
        }
    }
    @Preview
    @Composable
    fun PreviewGreeting() {
        Hello("Android")
    }
}