package com.plusinfosys.carousalview

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class TextCarouselViewPager : ViewPager {
    constructor(context: Context) : super(context) {
        this.mcontext = context;

        updateComponentPadding()
    }

     var mcontext: Context

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mcontext = context;
        updateComponentPadding()
    }

    private fun updateComponentPadding() {
        val horizontalPadding =
            getResources().getDimensionPixelSize(R.dimen.animated_text_carousel_view_pager_padding_horizontal)
        setPadding(horizontalPadding, getPaddingTop(), horizontalPadding, getPaddingBottom())
        requestLayout()
    }

    private fun setCaroselAdapter(items: List<String>): TextCarouselAdapter {
        val carroselAdapter = TextCarouselAdapter(mcontext,this, items)
        setAdapter(carroselAdapter)
        return carroselAdapter
    }

    fun setItems(items: List<String>) {
        setPageTransformer(
            false,
            TextCarouselPageTransformer(this, setCaroselAdapter(items))
        )
    }


    fun setItemsWithListener(items: List<String>, listener: TextCarouselListener) {
        setPageTransformer(
            false,
            TextCarouselPageTransformer(this, setCaroselAdapter(items), listener)
        )
    }
}
