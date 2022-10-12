package com.garibyan.armen.last_classwork.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.garibyan.armen.last_classwork.R
import com.garibyan.armen.last_classwork.common.Constants
import com.garibyan.armen.last_classwork.common.ViewType
import com.garibyan.armen.last_classwork.databinding.MessageItemBinding
import com.garibyan.armen.last_classwork.databinding.NonTextMessageItemBinding
import com.garibyan.armen.last_classwork.domain.model.Chat

class ChatAdapter : ListAdapter<Chat, ViewHolder>(ChatCallBack()) {

    inner class ChatViewHolder(private val binding: MessageItemBinding) : ViewHolder(binding.root) {
        fun bind(chatDto: Chat) = with(binding) {
            tvTime.text = chatDto.updatedDate
            imgAvatar.load(chatDto.avatar)
            tvFullname.text = "${chatDto.firstName} ${chatDto.lastName}"
            chatDto.lastMessage.let { tvMessage.text = it }
            if (chatDto.unreadMessage == 0) {
                tvUnreadMessages.visibility = View.GONE
                tvMessage.setTextColor(
                    ContextCompat.getColor(tvMessage.context, R.color.seen_text_colore)
                )
            } else {
                tvUnreadMessages.text = chatDto.unreadMessage.toString()
                tvUnreadMessages.visibility = View.VISIBLE
            }
            if (chatDto.isTyping!!) {
                imgTyping.visibility = View.VISIBLE
            }
        }
    }

    inner class NonTextMessageViewHolder(private val binding: NonTextMessageItemBinding) :
        ViewHolder(binding.root) {
        fun bind(chatDto: Chat) = with(binding) {
            tvTime.text = chatDto.updatedDate
            imgAvatar.load(chatDto.avatar)
            tvFullname.text = "${chatDto.firstName} ${chatDto.lastName}"
            if (chatDto.unreadMessage == 0) {
                tvUnreadMessages.visibility = View.GONE
                tvMessage.setTextColor(
                    ContextCompat.getColor(tvMessage.context, R.color.seen_text_colore)
                )
            } else {
                tvUnreadMessages.text = chatDto.unreadMessage.toString()
                tvUnreadMessages.visibility = View.VISIBLE
            }
            if (chatDto.isTyping!!) {
                imgTyping.visibility = View.VISIBLE
            }
            when (chatDto.messageType) {
                Constants.VOICE -> {
                    imgMessageType.load(R.drawable.ic_voice)
                    tvMessage.text = "Sent a voice Message"
                }
                Constants.ATTACHMENT -> {
                    imgMessageType.load(R.drawable.ic_attachment)
                    tvMessage.text = "Sent a attachment"
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).lastMessage) {
            null -> ViewType.NOT_TEXT_TYPE.type
            else -> ViewType.TEXT_TYPE.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewType.NOT_TEXT_TYPE.type -> NonTextMessageViewHolder(
                NonTextMessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ChatViewHolder(
                MessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ChatViewHolder -> holder.bind(getItem(position))
            is NonTextMessageViewHolder -> holder.bind(getItem(position))
        }
    }

    class ChatCallBack : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Chat, newItem: Chat) = oldItem == newItem
    }

}