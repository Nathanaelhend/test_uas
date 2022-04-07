package com.example.a160419034_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.model.Kost
import com.example.a160419034_ubayakost.model.Pesan
import kotlinx.android.synthetic.main.message_list_item.view.*

class MessageListAdapter(val messageList: ArrayList<Pesan>) : RecyclerView
.Adapter<MessageListAdapter.messageViewHolder>(){
    class messageViewHolder(var view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): messageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.message_list_item, parent, false)
        return messageViewHolder(view)
    }

    override fun onBindViewHolder(holder: messageViewHolder, position: Int) {
        val message = messageList[position]
        with(holder.view){
            txtMessage.text = message.isi
            buttonMessage.setOnClickListener {
                val action = MessageListFragmentDirections.actionToMessageDetail(message.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = messageList.size

    fun updateMessageList(newMessageList: ArrayList<Pesan>){
        messageList.clear()
        messageList.addAll(newMessageList)
        notifyDataSetChanged()
    }
}