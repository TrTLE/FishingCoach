package io.fishingcoach.model.recyclerview.fishlist

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import io.fishingcoach.FishDetailActivity
import io.fishingcoach.FishListActivity
import io.fishingcoach.R
import io.opencensus.resource.Resource
import kotlinx.android.synthetic.main.item_and_fish.view.*

class FishInThePlaceAdapter (val items : Array<FishInThePlace>, val activity : Activity) : RecyclerView.Adapter<FishInThePlaceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.item_and_fish, parent, false)
        return ViewHolder(lineView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.sub_item.removeAllViews()

        holder.bindFishHere(items[position], activity)

        holder.itemView.setOnClickListener {
            val expanded = items[position].isExpanded()
            items[position].setExpanded(!expanded)
            notifyItemChanged(position)
        }
    }

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

        fun bindFishHere(FishHere: FishInThePlace, activity: Activity) {
            with(FishHere) {
                itemView.fishName.text = Name
                Glide
                    .with(view)
                    .load(Pic)
                    .centerCrop()
                    .into(itemView.fishPic)

                for(fishingType in FishHere.FishingType){
                    val textView = TextView(view.context)
                    textView.height = 150
                    textView.text = fishingType.NAME
                    textView.textSize = 18.00F
                    textView.setTextColor(Color.BLUE)
                    val intent = Intent(view.context,FishDetailActivity::class.java)
                    intent.putExtra("FishingType", fishingType.NAME)
                    intent.putExtra("FishingTypeID", fishingType.ID)
                    intent.putExtra("PlaceID", FishHere.PlaceID)
                    intent.putExtra("FishID", FishHere.ID)
                    intent.putExtra("FishName", Name)

                    val sharedFishPic = Pic
                    intent.putExtra("FishPicture", sharedFishPic)
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        itemView.fishPic.transitionName = R.string.FISH_PICTURE.toString()
                        textView.setOnClickListener {
                            val options = ActivityOptions.
                                makeSceneTransitionAnimation(activity,itemView, R.string.FISH_PICTURE.toString())

                            ContextCompat.startActivity(view.context,intent,options.toBundle())
                        }
                        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }else {
                        textView.setOnClickListener {
                            ContextCompat.startActivity(
                                view.context, intent, null
                            )
                        }
                    }
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