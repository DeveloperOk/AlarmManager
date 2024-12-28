package com.enterprise.alarmmanager

import android.app.AlarmManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.enterprise.alarmmanager.ui.theme.AlarmManagerTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Medium post
        //https://medium.com/kotlin-t%C3%BCrkiye/3-androidde-alarm-manager-i%CC%87le-belirli-zamanda-bildirim-g%C3%B6nderme-23eb2871ded5

        //Don't forget the part which is added to the AndroidManifest.xml
        val appAlarmScheduler = AppAlarmScheduler(context = this)

        val reminderItem = ReminderItem(
            time = Calendar.getInstance().apply {
                //set(Calendar.HOUR_OF_DAY, 19)
                //set(Calendar.MINUTE, 0)
                set(Calendar.HOUR_OF_DAY, 1)
                set(Calendar.MINUTE, 37)
            }.timeInMillis,
            id = 1,
        )

        appAlarmScheduler
            .schedule(reminderItem)


        enableEdgeToEdge()
        setContent {
            AlarmManagerTheme {
                AlarmManagerApp()
            }
        }
    }
}

@Composable
fun AlarmManagerApp() {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize().background(color = Color.Green)){

        Scaffold(modifier = Modifier.systemBarsPadding().fillMaxSize()) { innerPadding ->

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(innerPadding).fillMaxSize()
                    .background(color = Color.White)){

                Text(text = stringResource(id = R.string.main_acitivity_alarm_manager_message))

            }

        }

    }

}