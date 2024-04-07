package com.base.basemvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentManager
import com.base.basemvvm.databinding.ActivityMainBinding
import com.base.basemvvm.presentation.NavigationManager
import com.base.basemvvm.presentation.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), FragmentManager.OnBackStackChangedListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NavigationManager.getInstance().init(this, supportFragmentManager, R.id.fragment_container)
    }

    override fun onBackStackChanged() {

    }
}