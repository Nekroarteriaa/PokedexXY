package com.nekroapps.pokedexxy.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nekroapps.pokedexxy.ConnectionData.PokeMoveDetail
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.R

class PokemonMovesAdapter (internal var context: Context,
                           internal var moves: ArrayList<PokeMoveDetail>//,
//                                var clickListener: ITypeBadgeListener
): RecyclerView.Adapter<PokemonMovesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PokemonMovesAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_move_card,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonMovesAdapter.MyViewHolder, position: Int) {
        val moveName = moves[position].name
        val typeSymbol = PokeBank.getSymbolImage(moves[position].type.name.capitalize())
        holder.moveCard.text = moveName
        holder.moveType.setImageResource(typeSymbol)
    }

    override fun getItemCount(): Int {
        return moves.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var moveCard: TextView = itemView.findViewById(R.id.pokemonMoveCard)
        val moveType: ImageView = itemView.findViewById(R.id.pokemonMoveTypeImage)

    }

}