package com.example.fakebot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakebot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var  adapter : FbAdapter
    private lateinit var  binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        binding.fbRecycler.layoutManager= LinearLayoutManager(this)
        adapter = FbAdapter(this)
        binding.fbRecycler.adapter=adapter

        viewModel.chatMessageLiveData.observe(this, Observer {
            chatMessageList ->
            adapter.submitList(chatMessageList)
            binding.fbRecycler.scrollToPosition(chatMessageList.size - 1)

        })

    adapter.onItemClickListener={
        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
    }
        setupSendMessage()
}

    private fun setupSendMessage() {

        binding.sendButton.setOnClickListener {
        val message = binding.questionText.text.toString()
        if (message.isEmpty()){
            Toast.makeText(this,"Debes Ingresar un Mensaje Porfa", Toast.LENGTH_SHORT).show()

        }else{
            val chatMessage = ChatMessage(message,true)
            viewModel.addMessage(chatMessage)
            viewModel.createResponse()
            binding.questionText.setText("")
        }
    }

    }

}