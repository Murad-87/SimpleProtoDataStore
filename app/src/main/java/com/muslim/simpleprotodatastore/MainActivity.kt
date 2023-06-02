package com.muslim.simpleprotodatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muslim.simpleprotodatastore.data.SettingsData
import com.muslim.simpleprotodatastore.proto_data_store.ProtoDataStoreManager
import com.muslim.simpleprotodatastore.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val protoDataStoreManager = ProtoDataStoreManager(this)
        setContent {
            SimpleProtoDataStoreTheme {
                val settingsState = protoDataStoreManager
                    .getSettings()
                    .collectAsState(initial = SettingsData())

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(settingsState.value.bgColor)
                ) {
                    MainScreen(protoDataStoreManager, settingsState.value.textSize)
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    protoDataStoreManager: ProtoDataStoreManager,
    textSize: Int
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .wrapContentHeight(align = Alignment.CenterVertically)

        ) {
            Text(
                text = "Some text",
                color = Color.White,
                fontSize = textSize.sp
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = BlueButton),
            onClick = {
                scope.launch {
                    protoDataStoreManager.saveSettings(
                        SettingsData(
                            15,
                            Blue.value
                        )
                    )
                }
            }) {
            Text(text = "Blue")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = RedButton),
            onClick = {
                scope.launch {
                    protoDataStoreManager.saveSettings(
                        SettingsData(
                            30,
                            Red.value
                        )
                    )
                }
            }) {
            Text(text = "Red")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = GreenButton),
            onClick = {
                scope.launch {
                    protoDataStoreManager.saveSettings(
                        SettingsData(
                            40,
                            Green.value
                        )
                    )
                }
            }) {
            Text(text = "Green")
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
