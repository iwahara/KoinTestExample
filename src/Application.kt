package com.example.test.coin

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false, testModule: Module? = null) {
    install(Koin) {
        modules(getModule())

        if (testing && testModule != null) {
            //テスト用のModuleで上書き
            modules(testModule)
        }
    }
    routing {
        val speaker by inject<ClockSpeaker>()
        get("/") {
            call.respondText("HELLO WORLD!" + speaker.speak(), contentType = ContentType.Text.Plain)
        }
    }
}

fun getModule(): Module {
    return module {
        factory { ClockNow() as Clock }
        factory { ClockSpeaker(get()) as ClockSpeaker }
    }
}

