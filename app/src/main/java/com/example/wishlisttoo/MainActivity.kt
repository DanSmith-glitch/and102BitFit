package com.example.wishlisttoo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var items = mutableListOf<WishListItem>()
    private lateinit var adapter: WishListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = findViewById<RecyclerView>(R.id.wishyrv)
        adapter = WishListAdapter(items)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val Name = findViewById<EditText>(R.id.Name)
        val price = findViewById<EditText>(R.id.price)
        val urlsite = findViewById<EditText>(R.id.urlsite)
        val savebutton = findViewById<Button>(R.id.savebutton)

        savebutton.setOnClickListener{
            val itemname = Name.text.toString()
            val itemprice = price.text.toString()
            val siteurl = urlsite.text.toString()

            if (itemname.isNotBlank() && itemprice.isNotBlank() && siteurl.isNotBlank()){
                val newItem = WishListItem(itemname, itemprice, siteurl)
                items.add(newItem)

                adapter.notifyItemInserted(items.size - 1)

                Name.text.clear()
                price.text.clear()
                urlsite.text.clear()
            }
        }
    }
}