package ru.ivos.shoplist.presentation

import android.app.Application
import ru.ivos.shoplist.di.DaggerApplicationComponent

class MainApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}