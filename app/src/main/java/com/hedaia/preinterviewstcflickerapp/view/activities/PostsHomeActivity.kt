package com.hedaia.preinterviewstcflickerapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hedaia.preinterviewstcflickerapp.R
import com.hedaia.preinterviewstcflickerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostsHomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}