package com.example.avtovokzal.findAdvert

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.drawer.KDrawerView
import com.agoda.kakao.navigation.KNavigationView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.avtovokzal.R
import org.hamcrest.Matcher

class AdvertsScreen : Screen<AdvertsScreen>() {
    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recycler_view)
    }, itemTypeBuilder = {
        itemType(::MainItem)
    })

    class MainItem(parent: Matcher<View>) : KRecyclerItem<MainItem>(parent) {
        val price: KTextView = KTextView(parent) { withId(R.id.price) }
        val date: KTextView = KTextView(parent) { withId(R.id.date) }
    }
}