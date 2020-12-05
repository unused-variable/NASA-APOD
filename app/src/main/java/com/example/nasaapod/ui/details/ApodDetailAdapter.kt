package com.example.nasaapod.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nasaapod.data.model.ApodImage
import com.example.nasaapod.databinding.ItemApodDetailBinding

class ApodDetailAdapter(private var apodImages: List<ApodImage> = listOf()) :
    RecyclerView.Adapter<ApodDetailAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemApodDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apodImage: ApodImage) {
            with(binding) {
                imageHolder.load(apodImage.hdUrl) {
                    crossfade(true)
                }
                title.text = apodImage.title
                description.text = apodImage.explanation
                date.text = apodImage.date
                mediaType.text = apodImage.mediaType
                serviceVersion.text = apodImage.serviceVersion
                apodImage.copyright?.let {
                    copyright.text = it
                    copyright.isVisible = true
                } ?: run { copyright.isVisible = false }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemApodDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apodImages[position])
    }

    override fun getItemCount(): Int = apodImages.size
}