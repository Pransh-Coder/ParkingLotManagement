package org.example

import java.util.UUID

data class Vehicle(
    val id: String = UUID.randomUUID().toString(),
    val vehicleType: VehicleType,
    val vehicleNumber: String
)

enum class VehicleType{
    BIKE,
    CAR
}