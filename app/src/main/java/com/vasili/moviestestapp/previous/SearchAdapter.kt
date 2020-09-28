package com.vasili.moviestestapp.previous

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vasili.moviestestapp.R
import com.vasili.moviestestapp.common.SearchRequest
import kotlinx.android.synthetic.main.item_search.view.*
import kotlinx.android.synthetic.main.item_video.view.title

class SearchAdapter(private val searches: List<SearchRequest>, val onItemClick: (SearchRequest) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = searches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.title.text = searches[position].title
        holder.view.year.text = searches[position].year
        holder.view.type.text = searches[position].type
        holder.view.setOnClickListener { onItemClick(searches[position]) }
    }
}