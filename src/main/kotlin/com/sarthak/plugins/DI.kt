package com.sarthak.plugins

import com.sarthak.di.mainModule
import io.ktor.application.*
import org.koin.core.context.startKoin

fun Application.configureDI() {
    startKoin { modules(mainModule) }
}
