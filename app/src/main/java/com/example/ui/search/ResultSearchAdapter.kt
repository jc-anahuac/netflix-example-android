package com.example.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.data.entities.MovieEntity
import com.example.fragmentos.databinding.ItemSearchMovieBinding
import com.example.utils.Const

class ResultSearchAdapter (
    private val listener: ResultSearchListener
    ): RecyclerView.Adapter<ResultSearchAdapter.ResultViewHolder>(), Filterable{

    interface ResultSearchListener{
        fun onClickedResult(movieId: Long)
    }

    private val items = ArrayList<MovieEntity>()
    private val itemsFilterable = ArrayList<MovieEntity>()

    fun setItems(items: List<MovieEntity>){
        this.items.clear()
        this.itemsFilterable.clear()
        this.items.addAll(items)
        this.itemsFilterable.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ItemSearchMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ResultViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = itemsFilterable.size

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(itemsFilterable[position])
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val query = p0?.toString()?.lowercase() ?: ""
                if(query.isEmpty()){
                    itemsFilterable.clear()
                    itemsFilterable.addAll(items)
                } else {
                    val filterList = items.filter {
                        it.title?.lowercase()?.contains(query) == true ||
                                it.overview?.lowercase()?.contains(query) == true
                    }
                    itemsFilterable.clear()
                    itemsFilterable.addAll(filterList)
                }
                val filterResult = FilterResults()
                filterResult.values = itemsFilterable
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                notifyDataSetChanged()
            }

        }
    }

    class ResultViewHolder(
        private val binding: ItemSearchMovieBinding,
        private val listener: ResultSearchListener
    ): ViewHolder(binding.root), OnClickListener {
        private lateinit var movie: MovieEntity

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(movie: MovieEntity){
            this.movie = movie
            binding.nameText.text = movie.title
            Glide.with(binding.root)
                .load(Const.MEDIA_URL + movie.backdropPath)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(binding.backdropImage)
        }

        override fun onClick(p0: View?) {
            listener.onClickedResult(movie.id)
        }
    }
}