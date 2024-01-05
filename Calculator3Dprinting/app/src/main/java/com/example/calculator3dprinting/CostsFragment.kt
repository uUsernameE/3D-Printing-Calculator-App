package com.example.calculator3dprinting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class CostsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.costs_fragment, container, false)
        val button: Button = view.findViewById(R.id.button)

        button.setOnClickListener {
            onClick(view)
        }

        return view
    }

    private fun onClick(view: View) {
        val spoolPrice: EditText = view.findViewById(R.id.spool_price)
        val spoolWeight: EditText = view.findViewById(R.id.spool_weight)
        val printWeight: EditText = view.findViewById(R.id.print_weight)
        val hourlyRate: EditText = view.findViewById(R.id.hourly_rate)
        val workHours: EditText = view.findViewById(R.id.work_hours)
        val result: TextView = view.findViewById(R.id.quote)

        val pricePerSpool =
            spoolPrice.text.toString().toDouble() / spoolWeight.text.toString().toDouble()
        val printPrice = printWeight.text.toString().toDouble() * pricePerSpool / 1000
        val labor =
            hourlyRate.text.toString().toDouble() * workHours.text.toString().toDouble() / 60
        val sum = (printPrice + labor).toString()

        result.text = sum
    }
}