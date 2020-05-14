package com.daniel.proiect

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.daniel.proiect.network.AmiiboData
import com.daniel.proiect.master.AmiiboGridAdapter
import com.daniel.proiect.master.RequestStatus

@BindingAdapter("imageUrl")
fun bindImage(view: ImageView, url: String?)
{
    url?.let {
        val uri = url.toUri().buildUpon().scheme("https").build()
        val options = RequestOptions().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
        Glide.with(view.context).load(uri).apply(options).into(view)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(view: RecyclerView, data: List<AmiiboData>?)
{
    val adapter = view.adapter as AmiiboGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(view: ImageView, status: RequestStatus?)
{
    when (status)
    {
        RequestStatus.LOADING ->
        {
            view.visibility = View.VISIBLE
            view.setImageResource(R.drawable.loading_animation)
        }
        RequestStatus.DONE ->
        {
            view.visibility = View.GONE
        }
        RequestStatus.ERROR ->
        {
            view.visibility = View.VISIBLE
            view.setImageResource(R.drawable.ic_connection_error)
        }
    }
}
