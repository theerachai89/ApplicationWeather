package com.android.weather.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.compose.ui.unit.Constraints
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.weather.R
import com.android.weather.constants.WeatherConstants
import com.android.weather.data.weather.model.ListItem
import com.android.weather.databinding.ItemWeatherBinding
import com.android.weather.extensions.toTime
import com.bumptech.glide.Glide

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var list: List<ListItem?> = listOf()
    var units: String = WeatherConstants.Units.metric

    fun setListData(list: List<ListItem?>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemWeatherBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ListItem,units:String) {
            binding.apply {
                tvTime.text = result.dtTxt?.toTime()
                tvTemp.text = when (units) {
                    WeatherConstants.Units.metric -> "${result.main?.temp?.toInt() ?: 0} °C"
                    else -> "${result.main?.temp?.toInt()} °F"
                }
                tvHumidity.text = "${result.main?.humidity ?: ""}%"

                Glide.with(context)
                    .load(context.getString(R.string.url_img, result.weather?.first()?.icon?: ""))
                    .into(icon)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateLayout = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemWeatherBinding.inflate(
                inflateLayout,
                parent,
                false
            ), parent.context
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position]?.let { holder.bind(it,units) }
    }

    override fun getItemCount(): Int = list.size ?: 0
}