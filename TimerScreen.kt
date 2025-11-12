package com.focusmate.ui.timer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.focusmate.viewmodel.TimerViewModel

@Composable
fun TimerScreen(viewModel: TimerViewModel) {
    val timeLeft by viewModel.timeLeft.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val isBreak by viewModel.isBreak.collectAsState()

    val minutes = (timeLeft / 60).toString().padStart(2, '0')
    val seconds = (timeLeft % 60).toString().padStart(2, '0')

    val title = if (isBreak) "휴식 시간" else "집중 시간"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (isBreak) MaterialTheme.colorScheme.secondaryContainer
        else MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$minutes:$seconds",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row {
                if (!isRunning) {
                    Button(onClick = { viewModel.startTimer() }) {
                        Text("시작")
                    }
                } else {
                    Button(onClick = { viewModel.pauseTimer() }) {
                        Text("일시정지")
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { viewModel.resetTimer() }) {
                    Text("리셋")
                }
            }
        }
    }
}
