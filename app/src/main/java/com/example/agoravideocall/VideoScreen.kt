package com.example.agoravideocall

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import io.agora.agorauikit_android.AgoraConnectionData
import io.agora.agorauikit_android.AgoraVideoViewer


@OptIn(ExperimentalUnsignedTypes::class)
@Composable
fun VideoScreen(
    roomName: String,
    // navigate up is used to leave the current call and move back
    onNavigateUp: () -> Unit = {},
    viewModel: VideoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    var agoraView : AgoraVideoViewer? = null
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {perms->
            viewModel.onPermissionsResult(
                acceptedAudioPermission = perms[android.Manifest.permission.RECORD_AUDIO] == true,
                acceptedCameraPermission = perms[android.Manifest.permission.CAMERA] == true,
            )
        }
    )
    LaunchedEffect(key1 = true){
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.CAMERA,
            )
        )
    }
    BackHandler {
         agoraView?.leaveChannel()
        // for navigating to last screen
        onNavigateUp()
    }
    if(viewModel.hasAudioPermission.value && viewModel.hasCameraPermission.value)
    {
        AndroidView(factory = {
            AgoraVideoViewer(it, connectionData = AgoraConnectionData(
                appId = APP_ID
            )).also {
                it.join(roomName)
                    agoraView = it }
        },
            modifier = Modifier.fillMaxSize())
    }

}

