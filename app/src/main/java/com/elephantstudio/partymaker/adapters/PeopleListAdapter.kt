package com.elephantstudio.partymaker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elephantstudio.partymaker.data.Person
import com.elephantstudio.partymaker.databinding.ItemPersonBinding

class PeopleListAdapter(
        private var peopleList: List<Person>
): RecyclerView.Adapter<PeopleListAdapter.PeopleListViewHolder>() {

    inner class PeopleListViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {

        return PeopleListViewHolder(ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {

        //Przydzielenie danych do widoku kafelka w RecyclerView
        holder.binding.apply {
            tvPersonName.text = peopleList[position].personName
            ivPersonImage.setImageDrawable(peopleList[position].personImage)
        }

        holder.binding.root.setOnClickListener{
            onItemClickListener?.let {
                it(peopleList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    private var onItemClickListener: ((Person) -> Unit)? = null

    fun setOnItemClickListener(listener: (Person) -> Unit) {
        onItemClickListener = listener
    }
}