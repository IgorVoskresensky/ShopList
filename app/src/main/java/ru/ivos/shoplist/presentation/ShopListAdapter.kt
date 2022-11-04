package ru.ivos.shoplist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import ru.ivos.shoplist.R
import ru.ivos.shoplist.databinding.ShopItemDisableBinding
import ru.ivos.shoplist.databinding.ShopItemEnableBinding
import ru.ivos.shoplist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null
    var onShopItemClickListener : ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ENABLE -> R.layout.shop_item_enable
            DISABLE -> R.layout.shop_item_disable
            else -> {throw  RuntimeException("Unknown type")}
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }

        when (binding) {
            is ShopItemEnableBinding -> {
                binding.shopItem = shopItem
            }
            is ShopItemDisableBinding -> {
                binding.shopItem = shopItem
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item.enabled) ENABLE else DISABLE
    }

    companion object {
        const val ENABLE = 1
        const val DISABLE = -1
        const val MAX_POOL_SIZE = 5
    }
}