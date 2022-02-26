package com.example.fakebot

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var _chatMessageLiveData: MutableLiveData<MutableList<ChatMessage>> = MutableLiveData()

    val chatMessageLiveData: LiveData<MutableList<ChatMessage>>
        get() = _chatMessageLiveData


    fun addMessage(chatMessage: ChatMessage) {

        val mutableList = _chatMessageLiveData.value!!
        mutableList.add(chatMessage)
        _chatMessageLiveData.value=mutableList

    }
}