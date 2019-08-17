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
import kotlinx.android.synthetic.main.item_and_detail.view.*
import kotlinx.android.synthetic.main.item_and_fish.view.*
import kotlinx.android.synthetic.main.item_and_fish.view.fishName
import kotlinx.android.synthetic.main.item_and_fish.view.fishPic
import okhttp3.internal.notify

class FishDetailAdapter (val items : Array<FishDetail>) : RecyclerView.Adapter<FishDetailAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.item_and_detail, parent, false)
        return ViewHolder(lineView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFishHere(items[position])

        holder.itemView.setOnClickListener {
            val expanded = items[position].isExpanded()
            items[position].setExpanded(!expanded)
            notifyItemChanged(position)
        }
    }


    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){

        //private val subItem = view.findViewById<View>(R.id.)
        //fonction qui permet de lier les données à la vue
        fun bindFishHere(FishHere: FishDetail) {
            with(FishHere) {
                val expanded = this.isExpanded()

                itemView.fishName.text = "$Name"
                itemView.fishPic.setImageResource(this.Pic)
                itemView.sub_item_material.text = "$Material"

                if (expanded)
                    itemView.sub_item.visibility = View.VISIBLE
                else
                    itemView.sub_item.visibility = View.GONE

            }
        }
    }
}