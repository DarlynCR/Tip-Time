package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipResult.isVisible = false

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {


        val cost = binding.costOfService.text.toString().toDoubleOrNull()

        if(cost != null){

            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId){

                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            var tip = tipPercentage * cost

            if(binding.roundUpSwitch.isChecked){
                tip = kotlin.math.ceil(tip)
            }

            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

            binding.tipResult.apply {
                isVisible = true
                text = getString(R.string.tip_amount, formattedTip)
            }
        }else {
            binding.tipResult.text = ""
            Toast.makeText(this, "Enter the cost of the service.", Toast.LENGTH_LONG).show()
        }




    }


}