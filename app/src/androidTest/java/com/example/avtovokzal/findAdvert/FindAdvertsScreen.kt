package com.example.avtovokzal.findAdvert

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.picker.date.KDatePickerDialog
import com.agoda.kakao.picker.time.KTimePickerDialog
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.avtovokzal.R

class FindAdvertsScreen : Screen<FindAdvertsScreen>(){
    val fromTV = KEditText { withId(R.id.fromTV) }
    val toTV = KEditText { withId(R.id.toTV) }
    val calendarButton = KButton{withId(R.id.calendarBtn)}
    val dateTV = KTextView{withId(R.id.dateTV)}
    val datePickerDialog: KDatePickerDialog = KDatePickerDialog()
    val timePickerDialog: KTimePickerDialog = KTimePickerDialog()
}