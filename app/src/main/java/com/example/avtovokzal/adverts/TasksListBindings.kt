/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.avtovokzal.adverts

import android.graphics.Paint
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avtovokzal.core.domain.AdvertModel
import java.util.*

/**
 * [BindingAdapter]s for the [Task]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<AdvertModel>) {
    Log.d("Nurs","set items $items")
    (listView.adapter as AdvertsAdapter).submitList(items)
}

@BindingAdapter("app:date")
fun setDate(textView: TextView, date: Date) {
    textView.text = "${date.year}/${date.month + 1}/${date.day}"
}

@BindingAdapter("app:time")
fun setTime(textView: TextView, date: Date) {
    textView.text = "${date.hours}:${date.minutes}"
}


@BindingAdapter("app:price")
fun setPrice(textView: TextView, advertModel: AdvertModel) {
    advertModel.price?.let {
        textView.text = "${advertModel.price} som"
    }
}

@BindingAdapter("app:completedTask")
fun setStyle(textView: TextView, enabled: Boolean) {
    if (enabled) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}