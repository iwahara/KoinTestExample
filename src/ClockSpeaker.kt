package com.example.test.coin

class ClockSpeaker(private val clock: Clock) {
    fun speak(): String {
        return clock.now().toString("yyyy年MM月dd日のHH:mm:ssやでー")
    }
}