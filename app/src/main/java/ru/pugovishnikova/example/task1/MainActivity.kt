package ru.pugovishnikova.example.task1

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(R.layout.activity_main), RectangleAdaptor.Listener {
    //private val rectangles = mutableListOf<Rectangle>()
    private val rectangles = mutableListOf<Int>()
    private val rectangleAdaptor = RectangleAdaptor(rectangles, this)
    private var num = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = findViewById<RecyclerView>(R.id.rcView)
        recyclerView.adapter = rectangleAdaptor
        if (getScreenOrientation())
            recyclerView.layoutManager = GridLayoutManager(this, 3)
        else recyclerView.layoutManager = GridLayoutManager(this, 4)
        val button = findViewById<Button>(R.id.buttonAdd)
        button.setOnClickListener {
            onAddClick()
        }
    }

    private fun onAddClick() {
        rectangles.add(generateRectangle())
        rectangleAdaptor.notifyDataSetChanged()
    }

//    override fun onRectangleClick(rectangle: Rectangle) {
//        Toast.makeText(this, rectangle.number, Toast.LENGTH_SHORT).show()
//    }

    override fun onRectangleClick(rectangle: Int) {
        Toast.makeText(this, rectangle, Toast.LENGTH_SHORT).show()
    }

    //    private fun generateRectangle(): Rectangle{
//        num++
//        val rec = Rectangle(num)
//        println(rec.number)
//        return rec
//    }
    private fun generateRectangle(): Int {
        num++
        return num
    }

    private fun getScreenOrientation(): Boolean {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> true
            else -> false
        }
    }
}