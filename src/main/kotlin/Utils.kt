package org.example

object Utils {
    private var ticketId = 1

    fun generateTicketId() : Int {
        return ticketId++
    }
}