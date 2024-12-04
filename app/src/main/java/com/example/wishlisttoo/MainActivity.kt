package com.example.wishlisttoo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var items = mutableListOf<DisplayTracker>()
    private lateinit var adapter: TrackerAdapter
    private lateinit var db: AppDatabase
    //private lateinit var trackerRepository: TrackerRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = AppDatabase.getInstance(applicationContext)
        //trackerRepository = TrackerRepository(db.trackerDao())

        val recyclerView = findViewById<RecyclerView>(R.id.wishyrv)
        adapter = TrackerAdapter(items)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val Name = findViewById<EditText>(R.id.Name)
        val cals = findViewById<EditText>(R.id.cals)
        val savebutton = findViewById<Button>(R.id.savebutton)

        lifecycleScope.launch {
            (application as TrackerApplication).db.trackerDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayTracker(
                        entity.itemf,
                        entity.calos
                    )
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }


        savebutton.setOnClickListener{
            val itemname = Name.text.toString()
            val itemcals = cals.text.toString()

            if (itemname.isNotBlank() && itemcals.isNotBlank()){
                val newItem = DisplayTracker(itemname, itemcals)
                lifecycleScope.launch(IO) {
                (application as TrackerApplication).db.trackerDao().insert(
                    TrackerEntity(
                        itemf = newItem.itemname,
                        calos = newItem.itemcals
                    )
                )
            }

                items.add(newItem)



                adapter.notifyItemInserted(items.size - 1)

                Name.text.clear()
                cals.text.clear()
            }
        }
    }
}