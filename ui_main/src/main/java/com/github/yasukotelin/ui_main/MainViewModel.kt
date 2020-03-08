package com.github.yasukotelin.ui_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var userCardState = MutableLiveData<UserCardState>().apply {
        value = UserCardState.OnNormal
    }

    fun onClickUserCard() {
        userCardState.postValue(UserCardState.OnDetails)
    }

    fun onClickBackAllow() {
        userCardState.postValue(UserCardState.OnNormal)
    }
}

enum class UserCardState {
    OnNormal, OnDetails
}
