package com.earl.nwcode.presentation.screens.selected

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.earl.nwcode.R
import com.earl.nwcode.databinding.FragmentImageBinding
import com.earl.nwcode.presentation.core.BaseFragment

class FragmentSelectedImage : BaseFragment<FragmentImageBinding>() {

    private var currentImageUrl = ""

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentImageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        binding.makeAsWallpaper.setOnClickListener {
            setWallpaper()
        }
    }

    private fun initView() {
        binding.progressBar.visibility = View.VISIBLE
        val link = arguments?.getString(IMAGE)
        currentImageUrl = link ?: ""
        Glide.with(binding.image.context).load(link).into(binding.image)
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun setWallpaper() {
        var bitmap: Bitmap? = null
        Glide.with(this)
            .asBitmap()
            .load(currentImageUrl)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })
        val manager = WallpaperManager.getInstance(requireActivity().applicationContext);
        try {
            if (bitmap != null) {
                manager.setBitmap(bitmap);
                Toast.makeText(requireContext(), context?.resources?.getString(R.string.success), Toast.LENGTH_SHORT).show()
            }
        } catch (e: java.lang.Exception) {
            Toast.makeText(requireContext(), context?.resources?.getString(R.string.fail), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = FragmentSelectedImage()
        private const val IMAGE = "image"
    }
}