package ru.ivos.shoplist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ivos.shoplist.R
import ru.ivos.shoplist.domain.ShopItem
import java.lang.RuntimeException

class ShopItemActivity : AppCompatActivity() {

//    private lateinit var viewModel: ShopItemViewModel
//
//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilCount: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var btnSave: Button

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        addTextChangeListeners()
        launchModeSetter()
//        observeViewModel()
    }

//    private fun observeViewModel() {
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_name)
//            } else {
//                null
//            }
//            tilName.error = message
//        }
//
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_count)
//            } else {
//                null
//            }
//            tilCount.error = message
//        }
//
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }

    private fun launchModeSetter() {
        val fragment = when (screenMode) {
            MODE_EDIT -> ShopItemFragment.newInstanceEditMode(shopItemId)
            MODE_ADD -> ShopItemFragment.newInstanceAddMode()
            else -> throw RuntimeException("Screen mode is unknown")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .commit()
    }

//    private fun addTextChangeListeners() {
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//        })
//
//        etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//        })
//    }

//    private fun initViews() {
//        tilName = findViewById(R.id.til_name)
//        tilCount = findViewById(R.id.til_count)
//        etName = findViewById(R.id.etName)
//        etCount = findViewById(R.id.etCount)
//        btnSave = findViewById(R.id.btn_save)
//    }
//
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//
//        btnSave.setOnClickListener {
//            viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
//        }
//    }
//
//    private fun launchAddMode() {
//        btnSave.setOnClickListener {
//            viewModel.addShopItem(etName.text?.toString(), etCount.text?.toString())
//        }
//    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Screen mode is unknown")
        }
        screenMode = mode

        if (screenMode == MODE_EDIT ) {
            if(!intent.hasExtra(EXTRA_SHOP_ITEM_ID)){
                throw RuntimeException("Screen shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }

    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra"
        private const val EXTRA_SHOP_ITEM_ID = "id"
        private const val MODE_EDIT = "edit mode"
        private const val MODE_ADD = "add mode"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddMode(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditMode(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}