package com.example.appdepizza

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appdepizza.databinding.ActivityPaymentBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class Payment : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        val name: String? = intent.extras?.getString("name")
        val amount: Int = intent.extras?.getInt("amount") ?: 0
        val total: Double = intent.extras?.getDouble("total") ?: 0.0
        val saucesAndDrinks: String? = intent.extras?.getString("saucesAndDrinks")
        val decimalFormat: NumberFormat = DecimalFormat.getCurrencyInstance()

        binding.txtTotal.text = "$name \n Amount: $amount \n Sauces And Drinks: $saucesAndDrinks \n Total: ${decimalFormat.format(total)}"

        binding.btPay.setOnClickListener {
            if (binding.btCreditCard.isChecked) {
                val intent = Intent(this, ThankYouScreen::class.java)
                startActivity(intent)
                Toast.makeText(this, "Card Payment", Toast.LENGTH_SHORT).show()
            } else if (binding.btPix.isChecked) {
                binding.editPix.visibility = View.VISIBLE
                val pix = binding.editPix.text.toString()
                if (pix.isNotEmpty()) {
                    val intent = Intent(this, ThankYouScreen::class.java)
                    intent.putExtra("usePix", pix)
                    startActivity(intent)
                    Toast.makeText(this, "Payment with Pix", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fill in the field", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
