package com.feritkeskin.fixerappclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feritkeskin.fixerappclone.databinding.FixerItemBinding
import com.feritkeskin.fixerappclone.model.CurrencyAndPrice

class FixerAdapter(
    private val arrayList: ArrayList<CurrencyAndPrice>
) : RecyclerView.Adapter<FixerAdapter.FixerViewHolder>() {

    class FixerViewHolder(val view: FixerItemBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = FixerItemBinding.inflate(inflater, parent, false)
        return FixerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FixerViewHolder, position: Int) {
        val list = arrayList[position]
        holder.view.tvItemCurrency.text = list.currency
        holder.view.tvItemPrice.text = list.price.toString()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}