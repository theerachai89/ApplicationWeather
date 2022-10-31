package com.android.weather.presentation.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.android.weather.databinding.SearchEdittextBinding

class SearchWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?
) : FrameLayout(context, attrs) {

    private val binding: SearchEdittextBinding by lazy {
        SearchEdittextBinding.inflate(LayoutInflater.from(context))
    }

    fun onTextChange(onTextChange:(String) -> Unit) = with(binding) {
        search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length >= 3) onTextChange.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    init {
        addView(binding.root)
    }


}