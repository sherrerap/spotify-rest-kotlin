package com.elpan.playlistmaker.application.routes

import com.elpan.playlistmaker.domain.model.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val users = mutableListOf(
    User(1, "FirstName LastName", 27, "test@elpan.com")
)

fun Route.userRouting(){
    route("/user"){
        get {
            if (users.isNotEmpty()){
                call.respond(users)
            } else {
                call.respondText("No users found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Id not found",
                status = HttpStatusCode.BadRequest
            )
            val user = users.find { it.id == id.toInt() } ?: return@get call.respondText(
                "User with $id not found",
                status = HttpStatusCode.NotFound
            )
            call.respond(user)
        }
        post {
            val user = call.receive<User>()
            users.add(user)
            call.respondText("User created successfully", status = HttpStatusCode.Created)
        }
        delete("{id?}"){
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Id not found",
                status = HttpStatusCode.BadRequest
            )
            if(users.removeIf {it.id == id.toInt()}){
                call.respondText("User deleted successfully", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("User not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}