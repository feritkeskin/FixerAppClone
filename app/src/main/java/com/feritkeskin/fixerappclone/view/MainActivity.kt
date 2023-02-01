package com.feritkeskin.fixerappclone.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.feritkeskin.fixerappclone.adapter.FixerAdapter
import com.feritkeskin.fixerappclone.databinding.ActivityMainBinding
import com.feritkeskin.fixerappclone.model.CurrencyAndPrice
import com.feritkeskin.fixerappclone.util.Contains.YYYY_MM_DD
import com.feritkeskin.fixerappclone.util.monthToTwoLength
import com.feritkeskin.fixerappclone.viewmodel.FixerViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FixerViewModel
    private var arrayList = ArrayList<CurrencyAndPrice>()
    private lateinit var binding: ActivityMainBinding
    private var startTime: String = ""
    private var endTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.progressFixer.visibility = View.VISIBLE
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FixerViewModel::class.java]
        val sdf = SimpleDateFormat(YYYY_MM_DD,Locale.getDefault())
        val instantDate = sdf.format(Date())
        startTime = instantDate
        endTime = instantDate
        viewModel.getFixerData(instantDate, instantDate)
        subscribeToObservers()
        listener()
    }

    private fun listener() {
        val today = Calendar.getInstance()

        binding.startPicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            startTime = "$year-${monthToTwoLength((month + 1).toString())}-$day"
        }
        binding.endPicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            endTime = "$year-${monthToTwoLength((month + 1).toString())}-$day"
        }

        binding.tvResult.setOnClickListener {
            binding.progressFixer.visibility = View.VISIBLE
            viewModel.getFixerData(startTime, endTime)
            subscribeToObservers()
        }
    }

    private fun subscribeToObservers() {
        viewModel.homeModel.observe(this) {
            if (it.success) {
                binding.tvMainCurrency.text = it.rates.TRY::class.simpleName
                binding.tvMainPrice.text = it.rates.TRY.end_rate.toString()
                arrayList.clear()
                arrayList.add(
                    CurrencyAndPrice(
                        currency = it.rates.USD::class.simpleName,
                        price = it.rates.USD.end_rate
                    )
                )
                arrayList.add(
                    CurrencyAndPrice(
                        currency = it.rates.EUR::class.simpleName,
                        price = it.rates.EUR.end_rate
                    )
                )
                arrayList.add(
                    CurrencyAndPrice(
                        currency = it.rates.GBP::class.simpleName,
                        price = it.rates.GBP.end_rate
                    )
                )
                views()
            } else {
                Toast.makeText(this, "Bir hata oluştu", Toast.LENGTH_SHORT).show()
                binding.progressFixer.visibility = View.GONE
            }
        }
    }

    private fun views() {
        val adapter = FixerAdapter(arrayList)
        binding.rvFixer.adapter = adapter
        binding.rvFixer.layoutManager = LinearLayoutManager(this)
        binding.progressFixer.visibility = View.GONE
    }
}