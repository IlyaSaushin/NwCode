package com.earl.nwcode.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earl.nwcode.R
import com.earl.nwcode.databinding.FragmentMainBinding
import com.earl.nwcode.presentation.core.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(), OnImageCategoryClickListener {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategoriesList()
    }

    private fun initCategoriesList() {
        val categoriesList = listOf(
            "backgrounds",
            "fashion",
            "nature",
            "science",
            "education",
            "feelings",
            "health",
            "people"
        )
        val adapter = ImageCategoryRecyclerAdapter(this)
        binding.categoriesRecycler.adapter = adapter
        adapter.submitList(categoriesList)
    }

    override fun onClick(item: String) {
        navigator.categories(item)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}