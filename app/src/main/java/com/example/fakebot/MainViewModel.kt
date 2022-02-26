package com.example.fakebot

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel: ViewModel() {

    private var _chatMessageLiveData: MutableLiveData<MutableList<ChatMessage>> = MutableLiveData()

    val chatMessageLiveData: LiveData<MutableList<ChatMessage>>
        get() = _chatMessageLiveData

    private val responses= arrayOf("Ni un poquito","Pregunta de Nuevo","No","Es muy probable que no ","No lo creo","Tal vez","No se :(")

    init {
        _chatMessageLiveData.value= mutableListOf()
    }


    fun addMessage(chatMessage: ChatMessage) {

        val mutableList = _chatMessageLiveData.value!!
        mutableList.add(chatMessage)
        _chatMessageLiveData.value=mutableList

    }

    fun createResponse() {

        val random= Random().nextInt(responses.size)
        val response= responses[random]

        val chatMessage = ChatMessage(response,false)
        val mutableList = _chatMessageLiveData.value!!
        mutableList.add(chatMessage)
        _chatMessageLiveData.value=mutableList

    }



}