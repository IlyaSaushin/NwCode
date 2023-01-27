package com.earl.nwcode.presentation.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.nwcode.databinding.FragmentSplashBinding
import com.earl.nwcode.presentation.core.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater, container, false)

    companion object {
        fun newInstance() = SplashFragment()
    }
}