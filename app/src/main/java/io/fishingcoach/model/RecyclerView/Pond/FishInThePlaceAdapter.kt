package io.fishingcoach.model.RecyclerView.Pond

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        holder.itemView.sub_item.removeAllViews()

        holder.bindFishHere(items[position])

        holder.itemView.setOnClickListener {
            val expanded = items[position].isExpanded()
            items[position].setExpanded(!expanded)
            notifyItemChanged(position)
        }
    }

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

        //fonction qui permet de lier les données à la vue
        fun bindFishHere(FishHere: FishInThePlace) {
            with(FishHere) {
                itemView.fishName.text = "$Name"
                Glide
                    .with(view)
                    .load(Pic)
                    .centerCrop()
                    .into(itemView.fishPic)


                for(fishingType in FishHere.FishingType){
                    val textView = TextView(view.context)
                    textView.text = fishingType.NAME
                    val intent = Intent(view.context,FishDetailActivity::class.java)
                    intent.putExtra("FishingType", fishingType.NAME)
                    textView.setOnClickListener{ ContextCompat.startActivity(view.context,intent,null) }
                    itemView.sub_item.addView(textView)
                }

                if (this.isExpanded())
                    itemView.sub_item.visibility = View.VISIBLE
                else
                    itemView.sub_item.visibility = View.GONE
            }
        }
    }
}