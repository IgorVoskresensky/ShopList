package ru.ivos.shoplist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ivos.shoplist.data.ShopListRepositoryImpl
import ru.ivos.shoplist.domain.DeleteShopItemUseCase
import ru.ivos.shoplist.domain.EditShopItemUseCase
import ru.ivos.shoplist.domain.GetShopListUseCase
import ru.ivos.shoplist.domain.ShopItem

class MainViewModel : ViewModel(){

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}