package com.leanscale.listdetails.features.gamesList.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leanscale.listdetails.R
import com.leanscale.listdetails.domain.models.Game


class GamesListAdapter(
    private var gamesList: ArrayList<Game>,
    val onItemClick: (Game) -> Unit,
    val onReachLastItem: () -> Unit
) :
    RecyclerView.Adapter<GamesListAdapter.CurrencyRateViewHolder>() {

    fun bindList(list: ArrayList<Game>) {
//        gamesList.let {
//           // it.addAll(list)
//            it.clear()
//            it.addAll(list)
//            notifyDataSetChanged()
//        }
        gamesList=list
        notifyDataSetChanged()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_list_item, parent, false)

        return CurrencyRateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyRateViewHolder, position: Int) {
        val game = gamesList[position]

        Glide
            .with(holder.itemView.context)
            .load(game.background_image)
            .centerCrop()
            //.placeholder(R.drawable.loading_spinner)
            .into(holder.iv_game)
        holder.tv_title.text = game.name
        holder.tv_shortDesc.text = game.released
        holder.tv_rating.text = game.rating?.toString()

        holder.itemView.setOnClickListener {
            onItemClick(game)
        }
        if (position == gamesList.size-1)
            onReachLastItem.invoke()

    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    class CurrencyRateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_game: AppCompatImageView
        val tv_title: AppCompatTextView
        val tv_shortDesc: AppCompatTextView
        val tv_rating: AppCompatTextView
        val tv_price: AppCompatTextView

        init {
            // Define click listener for the ViewHolder's View.
            iv_game = view.findViewById(R.id.iv_game)
            tv_title = view.findViewById(R.id.tv_title)
            tv_shortDesc = view.findViewById(R.id.tv_shortDesc)
            tv_rating = view.findViewById(R.id.tv_rating)
            tv_price = view.findViewById(R.id.tv_price)
        }
    }
}