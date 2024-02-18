package com.safi.datastoresample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.safi.datastoresample.databinding.ActivityEditUserInfoBinding
import com.safi.datastoresample.utils.PrefDatastore
import com.safi.datastoresample.utils.PrefKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EditUserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditUserInfoBinding
    private val prefDatastore by lazy { PrefDatastore(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            saveUserInfo()
        }

    }

    private fun saveUserInfo() {

        if (binding.userNameET.text.toString().isNotEmpty())
            prefDatastore.updateString(key = PrefKeys.userName, value = binding.userNameET.text.toString())

        if (binding.userEmailET.text.toString().isNotEmpty())
            prefDatastore.updateString(key = PrefKeys.userEmail, value = binding.userEmailET.text.toString())

        if (binding.userPhoneET.text.toString().isNotEmpty())
            prefDatastore.updateString(key = PrefKeys.userPhone, value = binding.userPhoneET.text.toString())

        finish()
    }
}