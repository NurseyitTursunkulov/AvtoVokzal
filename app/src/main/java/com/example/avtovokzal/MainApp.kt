package com.example.avtovokzal

import android.app.Application
import com.example.avtovokzal.core.domain.Cities
import com.example.avtovokzal.core.domain.CitiesImpl
import com.example.avtovokzal.core.domain.findAdd.FindingAdverts
import com.example.avtovokzal.core.domain.findAdd.FindingAdvertsImpl
import com.example.avtovokzal.core.domain.postAnAdd.SendAdvertImpl
import com.example.avtovokzal.core.domain.postAnAdd.SendingAdvert
import com.example.avtovokzal.postAdvert.PostAdvertViewModel
import com.example.avtovokzal.findAdvert.AdvertsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@MainApplication)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(myModule)
        }
    }


}

val myModule: Module = module {

    // ViewModel instance of MyViewModel
    // get() will resolve Repository instance
    viewModel {
        PostAdvertViewModel(
            sendAdvert = get(),
            gettingCities = get()
        )
    }
    viewModel{
        AdvertsViewModel(
            sendAdvert = get(),
            gettingCities = get(),
            findingAdverts = get())
    }
    single<FindingAdverts>{FindingAdvertsImpl()}
    single<SendingAdvert> { SendAdvertImpl() }
    single<Cities> { CitiesImpl() }

    // Single instance of Repository
//    single<Repository> { MyRepository() }
}