package ru.ivos.shoplist.presentation

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.ivos.shoplist.data.ShopListRepositoryImpl
import ru.ivos.shoplist.domain.DeleteShopItemUseCase
import ru.ivos.shoplist.domain.EditShopItemUseCase
import ru.ivos.shoplist.domain.GetShopListUseCase
import ru.ivos.shoplist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}