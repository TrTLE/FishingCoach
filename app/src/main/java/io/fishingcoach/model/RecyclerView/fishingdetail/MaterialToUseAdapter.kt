package io.fishingcoach.model.recyclerview.fishingdetail

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.fishingcoach.R
import kotlinx.android.synthetic.main.item_and_material.view.*
import android.graphics.Canvas
import android.graphics.Color
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_and_fish.view.*


class MaterialToUseAdapter (val items : Array<MaterialToUse>) : RecyclerView.Adapter<MaterialToUseAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.item_and_material, parent, false)
        return ViewHolder(lineView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMaterialHere(items[position])
    }

    class ViewHolder (val view : View) : RecyclerView.ViewHolder(view){

        fun bindMaterialHere(MaterialHere : MaterialToUse){
            with(MaterialHere){
                val picture = ImageView(view.context)

                itemView.materialName.text = Name

                Glide
                    .with(view.context)
                    .asBitmap()
                    .load(IMG)
                    .into(object : CustomTarget<Bitmap>(){
                        override fun onResourceReady(resource : Bitmap, transition : Transition<in Bitmap>?){
                            picture.setImageBitmap(resource)
                            itemView.materialPic.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
                view.setOnClickListener {
                    val dialog = object : Dialog(view.context,android.R.style.Theme_Translucent_NoTitleBar){
                        override fun onTouchEvent(event: MotionEvent): Boolean {
                            this.dismiss()
                            return true
                        }
                    }
                    dialog.window.setBackgroundDrawable(picture.drawable)
                    dialog.window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    dialog.show()
                    Log.i("MATERIAL IMAGE", "CLICK SUR ${itemView.materialName.text}")
                }
            }
        }
    }
}

