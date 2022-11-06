package ru.ivos.shoplist.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.ivos.shoplist.data.AppDatabase
import ru.ivos.shoplist.data.ShopListDao
import ru.ivos.shoplist.data.ShopListRepositoryImpl
import ru.ivos.shoplist.domain.ShopListRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDatabase.getInstance(application).getShopListDao()
        }
    }
}