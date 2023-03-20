package com.example.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.data.entities.MovieEntity
import com.example.fragmentos.databinding.ItemHomeHeaderBinding
import com.example.fragmentos.databinding.ItemMovieBinding
import com.example.utils.Const

class PopularMovieItemAdapter(private val listener: MovieItemListener):
    RecyclerView.Adapter<PopularMovieItemAdapter.HomeViewHolder>(){

    interface MovieItemListener {
        fun onClickedMovie(movieId: Long)
    }

    private val items = ArrayList<MovieEntity>()

    fun setItems(items: List<MovieEntity>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int){
        val item = items[position]
        holder.bind(item)
    }

    class HomeViewHolder(
        private val binding: ItemMovieBinding,
        private val listener: MovieItemListener
    ): RecyclerView.ViewHolder(binding.root), OnClickListener{

        private lateinit var movie: MovieEntity

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(movie: MovieEntity){
            this.movie = movie
            Glide.with(binding.root)
                .load(Const.MEDIA_URL + movie.backdropPath)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(binding.posterImage)
        }

        override fun onClick(p0: View?) {
            listener.onClickedMovie(movie.id)
        }
    }
}