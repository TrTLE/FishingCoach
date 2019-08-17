package io.fishingcoach.model.RecyclerView.Pond

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.fishingcoach.FishDetailActivity
import io.fishingcoach.R
import kotlinx.android.synthetic.main.item_and_fish.view.*

class FishInThePlaceAdapter (val items : Array<FishInThePlace>) : RecyclerView.Adapter<FishInThePlaceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.item_and_fish, parent, false)
        return ViewHolder(lineView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFishHere(items[position])
    }


    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){

        //fonction qui permet de lier les données à la vue
        fun bindFishHere(FishHere: FishInThePlace) {
            with(FishHere) {
                itemView.fishName.text = "$Name"
                itemView.fishPic.setImageResource(FishHere.Pic)
                val intent = Intent(view.context,FishDetailActivity::class.java)
                itemView.setOnClickListener { ContextCompat.startActivity(view.context,intent,null) }
            }
        }
    }
}