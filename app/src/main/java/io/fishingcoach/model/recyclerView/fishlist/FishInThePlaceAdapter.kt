package io.fishingcoach.model.recyclerView.fishlist

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.fishingcoach.FishDetailActivity
import io.fishingcoach.R
import io.fishingcoach.model.database.FishingType
import io.fishingcoach.model.enumeration.HmiSize
import kotlinx.android.synthetic.main.activity_fishlist.*
import kotlinx.android.synthetic.main.item_and_fish.view.*

class FishInThePlaceAdapter(
    private val items: List<FishInThePlace>,
    private val activity: Activity
) : RecyclerView.Adapter<FishInThePlaceAdapter.ViewHolder>() {

    private var previousExpandedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_and_fish, parent, false)
        return ViewHolder(lineView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val position = holder.adapterPosition

        val isExpanded = items[position].isExpanded()
        holder.itemView.sub_item.removeAllViews()
        holder.itemView.isActivated = isExpanded

        when(isExpanded) {
            true -> {
                holder.itemView.sub_item.visibility = View.VISIBLE
                previousExpandedPosition = position
            }
            else -> holder.itemView.sub_item.visibility = View.GONE
        }

        holder.bindFishHere(items[position], activity)

        holder.itemView.setOnClickListener {
            if (previousExpandedPosition != -1)
                items[previousExpandedPosition].setExpanded(false)
            items[position].setExpanded(!isExpanded)
            activity.FishInThePlaceRecyclerView.smoothScrollToPosition(position)
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(position)
        }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindFishHere(FishHere: FishInThePlace, activity: Activity) {
            with(FishHere) {
                itemView.fishName.text = Name
                Glide
                    .with(view)
                    .load(Pic)
                    .centerCrop()
                    .into(itemView.fishPic)

                FishHere.FishingType.forEach {
                    itemView.sub_item.addView(generateSubTextView(it, FishHere, activity))
                }
            }
        }

        private fun FishInThePlace.generateSubTextView(
            fishingType: FishingType,
            FishHere: FishInThePlace,
            activity: Activity
        ): TextView {
            val textView = TextView(view.context)
            textView.height = HmiSize.FISH_IN_THE_PLACE_ADAPTER.height
            textView.text = fishingType.NAME
            textView.textSize = HmiSize.FISH_IN_THE_PLACE_ADAPTER.textSize
            textView.setTextColor(Color.BLUE)

            val intent = Intent(view.context, FishDetailActivity::class.java)
            intent.putExtra("FishingType", fishingType.NAME)
            intent.putExtra("FishingTypeID", fishingType.ID)
            intent.putExtra("PlaceID", FishHere.PlaceID)
            intent.putExtra("FishID", FishHere.ID)
            intent.putExtra("FishName", Name)

            val sharedFishPic = Pic
            intent.putExtra("FishPicture", sharedFishPic)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                itemView.fishPic.transitionName = R.string.FISH_PICTURE.toString()
                textView.setOnClickListener {
                    val options = ActivityOptions.makeSceneTransitionAnimation(
                        activity,
                        itemView,
                        R.string.FISH_PICTURE.toString()
                    )
                    ContextCompat.startActivity(view.context, intent, options.toBundle())
                }
                textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

            } else {
                textView.setOnClickListener {
                    ContextCompat.startActivity(
                        view.context, intent, null
                    )
                }
            }

            return textView
        }
    }
}