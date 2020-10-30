package com.example.test.coin

import io.ktor.http.*
import io.ktor.server.testing.*
import org.joda.time.DateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({
            val now = DateTime(2020, 11, 29, 12, 23, 34)
            val testModule = org.koin.dsl.module(override = true) {
                factory { ClockSpecify(now) as Clock }
            }
            module(testing = true, testModule = testModule)
        }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!2020年11月29日の12:23:34やでー", response.content)
            }
        }
    }
}
