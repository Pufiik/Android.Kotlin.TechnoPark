package ru.pugovishnikova.example.task1

import android.graphics.drawable.Drawable
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class RectangleAdaptor(
    //private val rectangles: List<Rectangle>,
    private val rectangles: List<Int>,
    private val onRectangleClickListener: Listener
) : RecyclerView.Adapter<RectangleAdaptor.RectangleHolder>(){

    class RectangleHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val nameTextView: TextView = item.findViewById(R.id.textView_item)
        val context = item.context
        private val layoutView: CardView = item.findViewById(R.id.cardView_item)
        fun bind(rectangle: Int) {
            nameTextView.text = rectangle.toString()
            if (rectangle % 2 == 0) {
                layoutView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
            }
            else {layoutView.setBackgroundColor(ContextCompat.getColor(context, R.color.blue))}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RectangleHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rectangle_item, parent, false)
        view.setOnClickListener { v: View -> onRectangleClickListener.onRectangleClick(v.tag as Int)}
        return RectangleHolder(view)
    }

    override fun getItemCount(): Int {
        return rectangles.size
    }

    override fun onBindViewHolder(holder: RectangleHolder, i: Int) {
        val rec = rectangles[i]
        holder.bind(rec)
        holder.itemView.tag = rec
    }

    interface Listener {
        fun onRectangleClick(rectangle: Int)
    }
}