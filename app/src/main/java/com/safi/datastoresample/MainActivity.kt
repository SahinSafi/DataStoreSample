package com.safi.datastoresample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import androidx.lifecycle.lifecycleScope
import com.safi.datastoresample.databinding.ActivityMainBinding
import com.safi.datastoresample.utils.PrefDatastore
import com.safi.datastoresample.utils.PrefKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val prefDatastore by lazy { PrefDatastore(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editUserInfoTV.setOnClickListener {
            startActivity(Intent(this, EditUserInfoActivity::class.java))
        }

        observeUserInfo()
    }

    private fun observeUserInfo() {

        CoroutineScope(Dispatchers.Main).launch {
            prefDatastore.observeString(PrefKeys.userName).collect {
                binding.userNameValueTV.text = it
            }
            prefDatastore.observeString(PrefKeys.userEmail).collect {
                binding.userEmailValueTV.text = it
            }
        }

        lifecycleScope.launch {

        }

        lifecycleScope.launch {

        }

        lifecycleScope.launch {
            prefDatastore.observeString(PrefKeys.userPhone).collect {
                binding.userPhoneValueTV.text = it
            }
        }
    }
}