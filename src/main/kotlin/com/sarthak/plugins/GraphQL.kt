package com.sarthak.plugins

import com.apurebase.kgraphql.GraphQL
import io.ktor.application.*

fun Application.configureGraphQl() {
    install(GraphQL) {
        playground = true
        schema { }
    }
}
