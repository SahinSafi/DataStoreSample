package com.safi.datastoresample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.safi.datastoresample.databinding.ActivityMainBinding
import com.safi.datastoresample.utils.PrefDatastore
import com.safi.datastoresample.utils.PrefKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val prefDatastore by lazy { PrefDatastore(this) }

    private val flow1 = flow {
        delay(1000)
        emit("Flow1")
    }
    private val flow2 = flow {
        delay(3000)
        emit("Flow2")
    }

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

        lifecycleScope.launch {
            flow1.collect {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            }

            flow2.collect {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            }
        }

        lifecycleScope.launch {
            prefDatastore.observeString(PrefKeys.userName).collect {
                binding.userNameValueTV.text = it
            }
        }

        lifecycleScope.launch {
            prefDatastore.observeString(PrefKeys.userEmail).collect {
                binding.userEmailValueTV.text = it
            }
        }

        lifecycleScope.launch {
            prefDatastore.observeString(PrefKeys.userPhone).collect {
                binding.userPhoneValueTV.text = it
            }
        }
    }
}