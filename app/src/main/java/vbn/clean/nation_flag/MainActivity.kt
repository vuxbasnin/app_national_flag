package vbn.clean.nation_flag

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.AndroidEntryPoint
import vbn.clean.nation_flag.databinding.ActivityMainBinding
import vbn.clean.nation_flag.presentation.NavigationManager
import vbn.clean.nation_flag.presentation.core.base.BaseActivity

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