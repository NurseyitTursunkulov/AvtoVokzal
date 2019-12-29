package com.example.avtovokzal.adverts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avtovokzal.R
import com.example.avtovokzal.findAdvert.SlideshowViewModel
import kotlinx.android.synthetic.main.advert_item.view.*
import java.text.SimpleDateFormat

class AdvertsAdapter(val slideShowViewModel: SlideshowViewModel)
    : RecyclerView.Adapter<AdvertViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.advert_item,parent,false)
        return AdvertViewHolder(view)
    }

    override fun getItemCount(): Int {
        return slideShowViewModel.advertsLoadedEvent.value?.peekContent()?.size ?: 0
    }

    override fun onBindViewHolder(holder: AdvertViewHolder, position: Int) {
            with(slideShowViewModel.advertsLoadedEvent.value?.peekContent()?.get(position)){
            holder.from.text = this?.fromCity
            holder.to.text = this?.toCity
            holder.date.text =  SimpleDateFormat(" hh:mm:ss").format(this?.date)
            holder.price.text = this?.price + "com"
        }
    }
}

class AdvertViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val from = view.from
    val to = view.to
    val date = view.date
    val price = view.price
}