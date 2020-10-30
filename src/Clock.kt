package com.example.test.coin

import org.joda.time.DateTime

/**
 * 現在日時を取得するためのinterface
 */
interface Clock {
    fun now(): DateTime
}

/**
 * 現在日時を取得する
 */
class ClockNow : Clock {
    override fun now(): DateTime {
        return DateTime()
    }
}

/**
 * 指定された日時を取得する
 */
class ClockSpecify(private val specifyDateTime: DateTime) : Clock {
    override fun now(): DateTime {
        return specifyDateTime
    }
}