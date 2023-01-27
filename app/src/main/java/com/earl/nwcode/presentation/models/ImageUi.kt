package com.earl.nwcode.presentation.models

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.earl.nwcode.presentation.core.Same

interface ImageUi : Same<ImageUi> {

    override fun same(value: ImageUi) = value == this

    fun recyclerDetails(view: ImageView, bar: ProgressBar)

    fun provideLargeImageLink() : String

    class Base(
        private val id: Int,
        private val standardUrl: String,
        private val preview: String,
        private val large: String
    ) : ImageUi {

        override fun recyclerDetails(view: ImageView, bar: ProgressBar) {
            bar.visibility = View.VISIBLE
            Glide.with(view.context).load(preview).into(view)
            bar.visibility = View.INVISIBLE
        }

        override fun provideLargeImageLink() = large
    }
}