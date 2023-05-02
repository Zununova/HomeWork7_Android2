package com.example.homework7_android2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework7_android2.databinding.ItemBinding

class ChatAdapter(private var list: ArrayList<String>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    fun setList(chatMassage: ArrayList<String>) {
        list = chatMassage
        notifyItemInserted(chatMassage.size - 1)
    }

    class ChatViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {
            binding.textViewText.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}