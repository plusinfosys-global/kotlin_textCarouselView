package com.example.kotlindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlindemo.databinding.ActivityAnimatedTextCarouselSampleBinding
import com.plusinfosys.carousalview.TextCarouselListener
import java.util.*

class MainActivity : AppCompatActivity(), TextCarouselListener {


    override fun onTextSelected(selectedText: String) {
    }

    private lateinit var binding: ActivityAnimatedTextCarouselSampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_animated_text_carousel_sample)
        loadCarosel()
    }

    internal fun loadCarosel() {
        val items = ArrayList<String>()
        for (i in 1..10) {
            items.add(i.toString())
        }
        binding?.carroselViewPager?.setItemsWithListener(items, this)
    }

}
