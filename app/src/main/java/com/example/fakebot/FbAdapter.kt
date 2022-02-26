package com.example.fakebot

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fakebot.databinding.FbListItemBinding


private val TAG= FbAdapter::class.java.simpleName

class FbAdapter(private val context: Context): ListAdapter<ChatMessage, FbAdapter.FbViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<ChatMessage>() {

        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem.message == newItem.message
        }

        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }

    }

    lateinit var onItemClickListener: (ChatMessage) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FbViewHolder {

        val binding = FbListItemBinding.inflate(LayoutInflater.from(parent.context))
        return FbViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FbViewHolder, position: Int) {
        val chatMessage = getItem(position)
        holder.bind(chatMessage)

    }

    inner class FbViewHolder(private val binding: FbListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chatMessage: ChatMessage) {

            val chatListItemMessage = binding.fbText
            if (chatMessage.isQuestion) {

                chatListItemMessage.gravity = Gravity.END

            } else {

                chatListItemMessage.gravity = Gravity.START

            }

            chatListItemMessage.text = chatMessage.message.toString()
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(chatMessage)
                } else {
                    Log.e(TAG, "onItemClickListener not Initialized")
                }


            }

        }

    }
}