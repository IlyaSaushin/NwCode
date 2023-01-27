package com.earl.nwcode.presentation

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.earl.nwcode.R
import com.earl.nwcode.presentation.core.NavigationContract
import com.earl.nwcode.presentation.screens.SplashFragment
import com.earl.nwcode.presentation.screens.categories.FragmentCategoryImage
import com.earl.nwcode.presentation.screens.selected.FragmentSelectedImage
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), NavigationContract {

    var progressBar: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main()
    }

    override fun splash() {
        lifecycleScope.launch(Dispatchers.IO) {
            showFragment(SplashFragment.newInstance())
            delay(500)
            withContext(Dispatchers.Main) {
                main()
            }
        }
    }

    override fun main() {
        showFragment(MainFragment.newInstance())
    }

    override fun categories(category: String) {
        val fragment = FragmentCategoryImage.newInstance()
        val bundle = Bundle()
        bundle.putString(CATEGORY, category)
        fragment.arguments = bundle
        showFragment(fragment)
    }

    override fun selectedImage(image: String) {
        val fragment = FragmentSelectedImage.newInstance()
        val bundle = Bundle()
        bundle.putString(IMAGE, image)
        fragment.arguments = bundle
        showFragment(fragment)
    }

    override fun showProgressBar() {
        progressBar = Dialog(this, android.R.style.Theme_Black)
        val view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null)
        progressBar!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressBar!!.window!!.setBackgroundDrawableResource(R.color.custom_transparent)
        progressBar!!.setContentView(view)
        progressBar!!.show()
    }

    override fun hideProgressBar() {
        progressBar?.dismiss()
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val CATEGORY = "category"
        private const val IMAGE = "image"
    }
}