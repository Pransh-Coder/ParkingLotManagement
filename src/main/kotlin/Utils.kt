package org.example

object Utils {
    private var ticketId = 1

    fun generateTicketId() : Int {
        return ticketId++
    }

    fun getParkingSpots(): MutableList<ParkingSpot> {
        val parkSpotsList = mutableListOf<ParkingSpot>(
            ParkingSpot(id = "B1", vehicleType = VehicleType.BIKE, isOccupied = false),
            ParkingSpot(id = "B2", vehicleType = VehicleType.BIKE, isOccupied = false),
            ParkingSpot(id = "C1", vehicleType = VehicleType.CAR, isOccupied = false),
            ParkingSpot(id = "C2", vehicleType = VehicleType.CAR, isOccupied = false)
        )

        return parkSpotsList
    }
}