package com.earl.nwcode.presentation.screens.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.earl.nwcode.App
import com.earl.nwcode.R
import com.earl.nwcode.databinding.FragmentCategoryImageBinding
import com.earl.nwcode.presentation.core.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FragmentCategoryImage : BaseFragment<FragmentCategoryImageBinding>(), OnImageClickListener {

    @Inject lateinit var viewModel: FragmentCategoryImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCategoryImageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initExceptionHandler()
        val category = initCategory()
        if (category != null) {
            viewModel.fetchPicturesForCategory(category)
            binding.imageCategory.text = context?.resources?.getString(R.string.image_of_category, category)
        }
    }

    private fun initRecycler() {
        val adapter = ImagesRecyclerAdapter(this)
        binding.categoriesRecycler.adapter = adapter
        lifecycleScope.launchWhenStarted {
            viewModel._images.onEach { images ->
                if (images.isNotEmpty()) {
                    binding.progressBar.visibility = View.INVISIBLE
                    adapter.submitList(images)
                } else binding.progressBar.visibility = View.VISIBLE
            }.collect()
        }
    }

    private fun initExceptionHandler() {
        lifecycleScope.launchWhenStarted {
            viewModel._requestError.onEach { error ->
                if (error != null) {
                    Toast.makeText(requireContext(), context?.resources?.getString(R.string.request_error, error.message), Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }.collect()
        }
    }

    private fun initCategory() = arguments?.getString(CATEGORY)

    override fun onImageClick(image: String) {
        navigator.selectedImage(image)
    }

    companion object {
        fun newInstance() = FragmentCategoryImage()
        private const val CATEGORY = "category"
    }
}