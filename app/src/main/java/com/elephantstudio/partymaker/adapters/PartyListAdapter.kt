package com.elephantstudio.partymaker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elephantstudio.partymaker.data.Party
import com.elephantstudio.partymaker.databinding.ItemPartyBinding

class PartyListAdapter(
    private var partyList: List<Party>
) : RecyclerView.Adapter<PartyListAdapter.PartyListViewHolder>(){

    inner class PartyListViewHolder(val binding: ItemPartyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyListViewHolder {

        return PartyListViewHolder(ItemPartyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PartyListViewHolder, position: Int) {

        //Przydzielanie danych do widoku kafelka w RecycerView
        holder.binding.apply {
            tvPartyName.text = partyList[position].partyName
            tvPartyDate.text = partyList[position].partyDate
        }

        holder.binding.root.setOnClickListener {
            onItemClickListener?.let {
                it(partyList[position])
            }
        }
    }

    override fun getItemCount(): Int {

        return partyList.size
    }

    private var onItemClickListener: ((Party) -> Unit)? = null

    fun setOnItemClickListener(listener: (Party) -> Unit) {
        onItemClickListener = listener
    }
}