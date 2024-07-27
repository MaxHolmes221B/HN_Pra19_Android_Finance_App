package com.example.fina.screen

import com.example.fina.R
import com.example.fina.screen.home.HomeFragment
import com.example.fina.utils.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(HomeFragment::javaClass.name)
            .replace(R.id.layoutContainer, HomeFragment.newInstance())
            .commit()
    }
}