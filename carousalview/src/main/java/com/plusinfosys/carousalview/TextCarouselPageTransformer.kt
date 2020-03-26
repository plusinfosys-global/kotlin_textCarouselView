package com.plusinfosys.carousalview

import android.util.TypedValue
import android.view.View
import androidx.viewpager.widget.ViewPager

class TextCarouselPageTransformer : ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private var fontScaleFactor: Float = 0.0f
    private var textSizePxl: Float = 0.0f
    private var deselectedItemAlpha: Float = 0.0f

    private var listener: TextCarouselListener? = null
    private var adapter: TextCarouselAdapter? = null
    private var lastOffset: Float = 0.toFloat()


    constructor(pager: ViewPager, adapter: TextCarouselAdapter){
        this.adapter = adapter
        this.textSizePxl = pager.resources.getDimensionPixelSize(R.dimen.animated_text_carousel_text_size)  .toFloat()

        val alphaTypedValue = TypedValue()
        pager.resources.getValue(
            R.dimen.animated_text_carousel_deselected_text_alpha,
            alphaTypedValue,
            true
        )
        deselectedItemAlpha = alphaTypedValue.float

        val scaleFactorValue = TypedValue()
        pager.resources.getValue(
            R.dimen.animated_text_carousel_scale_factor,
            scaleFactorValue,
            true
        )
        fontScaleFactor = scaleFactorValue.float

        pager.addOnPageChangeListener(this)
    }

    constructor(pager: ViewPager, adapter: TextCarouselAdapter, listener: TextCarouselListener) {

        this.listener = listener
        this.adapter = adapter
        this.textSizePxl = pager.resources.getDimensionPixelSize(R.dimen.animated_text_carousel_text_size)  .toFloat()

        val alphaTypedValue = TypedValue()
        pager.resources.getValue(
            R.dimen.animated_text_carousel_deselected_text_alpha,
            alphaTypedValue,
            true
        )
        deselectedItemAlpha = alphaTypedValue.float

        val scaleFactorValue = TypedValue()
        pager.resources.getValue(
            R.dimen.animated_text_carousel_scale_factor,
            scaleFactorValue,
            true
        )
        fontScaleFactor = scaleFactorValue.float

        pager.addOnPageChangeListener(this)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val realCurrentPosition: Int
        val nextPosition: Int
        val isSwipingLeft = lastOffset > positionOffset
        val realOffset: Float

        if (isSwipingLeft) {
            realCurrentPosition = position + 1
            nextPosition = position
            realOffset = 1 - positionOffset
        } else {
            nextPosition = position + 1
            realCurrentPosition = position
            realOffset = positionOffset
        }

        // Avoid crash on overscroll
        if (nextPosition > adapter!!.count - 1 || realCurrentPosition > adapter!!.count - 1) {
            return
        }

        val currentText = adapter!!.getCarroselTextAt(realCurrentPosition)
        val nextText = adapter!!.getCarroselTextAt(nextPosition)

        if (currentText != null) {
            currentText.textSize = fontScaleFactor * (1 - realOffset) + textSizePxl
            currentText.alpha = 1 - realOffset + deselectedItemAlpha
        }

        if (nextText != null) {
            nextText.textSize = fontScaleFactor * realOffset + textSizePxl
            nextText.alpha = realOffset + deselectedItemAlpha
        }

        lastOffset = positionOffset
    }

    override fun transformPage(page: View, position: Float) {

    }

    override fun onPageSelected(position: Int) {
        if (adapter == null) return

        val selectedText = adapter!!.getCarroselTextAt(position)

        if (listener == null
            || selectedText == null
            || selectedText.text == null
            || selectedText.text.toString() == null
        )
            return

        listener!!.onTextSelected(selectedText.text.toString())
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}
