package com.example.avtovokzal

import android.app.Application
import com.example.avtovokzal.postAnAdd.SendAdvertImpl
import com.example.avtovokzal.postAnAdd.SendingAdert
import com.example.avtovokzal.ui.gallery.GalleryViewModel
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
val myModule : Module = module {

    // ViewModel instance of MyViewModel
    // get() will resolve Repository instance
    viewModel{ GalleryViewModel(get()) }
    single<SendingAdert> { SendAdvertImpl() }

    // Single instance of Repository
//    single<Repository> { MyRepository() }
}