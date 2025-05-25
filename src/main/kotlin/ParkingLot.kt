package org.example

import java.util.UUID

data class ParkingLot(
    val id: String,
    val vehicleType: VehicleType,
    val isOccupied: Boolean
)
