package org.example

import java.time.LocalDateTime

data class Ticket(
    val ticketId: Int = Utils.generateTicketId(),
    val vehicle: Vehicle,
    val timeStamp: LocalDateTime = LocalDateTime.now(),
    val spot: ParkingLot
)
