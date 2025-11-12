package com.focusmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private var timerJob: Job? = null

    private val _timeLeft = MutableStateFlow(25 * 60) // 기본 25분
    val timeLeft = _timeLeft.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    private val _isBreak = MutableStateFlow(false)
    val isBreak = _isBreak.asStateFlow()

    fun startTimer() {
        if (_isRunning.value) return

        _isRunning.value = true
        timerJob = viewModelScope.launch {
            while (_timeLeft.value > 0 && _isRunning.value) {
                delay(1000L)
                _timeLeft.value -= 1
            }
            if (_timeLeft.value == 0) {
                _isRunning.value = false
                if (!_isBreak.value) {
                    // 집중 끝 → 휴식 타임으로 전환
                    _isBreak.value = true
                    _timeLeft.value = 5 * 60 // 5분 휴식
                    startTimer()
                } else {
                    // 휴식 끝 → 초기화
                    resetTimer()
                }
            }
        }
    }

    fun pauseTimer() {
        _isRunning.value = false
        timerJob?.cancel()
    }

    fun resetTimer() {
        timerJob?.cancel()
        _isRunning.value = false
        _isBreak.value = false
        _timeLeft.value = 25 * 60
    }
}
