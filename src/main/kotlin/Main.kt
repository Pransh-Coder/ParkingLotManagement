package org.example

import java.time.LocalDateTime

/* Data Models

Vehicle
id
vehicleType
vehicleNumber


Ticket
id
Vehicle
timeStamp
parkingSpot


ParkingLot
id,
vehicleType
isOccupied

 */
fun main() {
    //println("Hello World!")

    val parkingRepo = ParkingRepository()

    parkingRepo.parkVehicle(vehicleNumber = "UP32NH12903", vehicleType = VehicleType.BIKE)
    parkingRepo.parkVehicle(vehicleNumber = "UP32NH89890", vehicleType = VehicleType.BIKE)
    parkingRepo.parkVehicle(vehicleNumber = "UP32BL0153", vehicleType = VehicleType.CAR)
    parkingRepo.parkVehicle(vehicleNumber = "UP32BL1234", vehicleType = VehicleType.CAR)

    println()

    Thread.sleep(2000)

    parkingRepo.unparkVehicle(ticketId = 1)

    Thread.sleep(5000)
    parkingRepo.unparkVehicle(ticketId = 4)
}