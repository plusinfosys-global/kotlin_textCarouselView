package com.plusinfosys.carousalview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.plusinfosys.carousalview.databinding.CarroselItemBinding
import java.util.ArrayList

class TextCarouselAdapter : PagerAdapter {

    private var viewPager: TextCarouselViewPager? = null
    var textViewsList: MutableList<TextView>
    private var texts: List<String>
    var context: Context

    constructor(context: Context, viewPager1: TextCarouselViewPager, texts: List<String>) {
        this.viewPager = viewPager1
        this.texts = texts
        this.context = context
        textViewsList = ArrayList()
        initTextViewArray(texts)
    }

    private fun initTextViewArray(texts: List<String>?) {
        if (texts != null) {

            for (model in texts) {
                textViewsList.add(TextView(context))
            }
        }
    }

    fun getCarroselTextAt(position: Int): TextView {
        return textViewsList[position]
    }

    override fun getCount(): Int {
        return texts.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarroselItemBinding.inflate(inflater, parent, false)
        parent.addView(binding.getRoot())
        binding.getRoot().setOnClickListener(View.OnClickListener {
            if (viewPager != null) {
                viewPager!!.currentItem = position
            }
        })
        bind(texts[position], binding)
        textViewsList[position] = binding.carrosselItemText
        return binding.getRoot()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private fun bind(text: String, binding: CarroselItemBinding) {
        binding.setVariable(1, text)
        binding.executePendingBindings()
    }
}
