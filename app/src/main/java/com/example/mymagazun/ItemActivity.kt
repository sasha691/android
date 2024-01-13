package com.example.mymagazun

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.razorpay.Checkout
import org.json.JSONObject
import java.lang.Exception

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        Checkout.preload(applicationContext)
        val co = Checkout()

        co.setKeyID("rzp_test_YCjCFQIB4i6u9B")

        val title: TextView = findViewById(R.id.item_list_title_one)
        val text: TextView = findViewById(R.id.item_list_text)
        val btn: Button = findViewById(R.id.btn_buy)

        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")
        btn.setOnClickListener { startPayment() }
    }

    private fun startPayment() {
        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name", "My Comani")
            options.put("description", "Demoing Charges")

            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc")
            options.put("currency", "USD")
            options.put("order_id", "order_DBJOWzybf0sJbb")
            options.put("amount", 10000)

            co.open(activity, options)
        }catch (e: Exception) {
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}