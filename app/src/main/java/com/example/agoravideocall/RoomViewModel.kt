package com.example.agoravideocall

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {
     private var _roomName = mutableStateOf(TextFieldState())
    val roomName : State<TextFieldState> = _roomName

     //when we sucessfully join the room we will navigate to next screen
    private val _onJoinEvent = MutableSharedFlow<String>()
     // string is the room name we wanna join
    val onJoinEvent = _onJoinEvent.asSharedFlow()

    // here we will just update our state
    fun onRoomEnter(name: String){
         _roomName.value = roomName.value.copy(
             text = name
         )
    }

    // function for actually joining room
    fun onJoinRoom()
    {
        if(roomName.value.text.isBlank()){
            _roomName.value = roomName.value.copy(
                error = "The room can't be empty"
            )
            return
        }
        // if roomname is not empty
        // we will launch couroutine scope
        viewModelScope.launch {
            _onJoinEvent.emit(roomName.value.text)
        }
    }


}