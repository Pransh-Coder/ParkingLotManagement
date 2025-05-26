package org.example

import java.time.Duration
import java.time.LocalDateTime

class ParkingRepository {

    //1. Park vehicle
    //2. Unpark vehicle
    //3. Fare Calculation (time-based calculation)

    //(Assign the nearest available spot)

    //private val spots : ArrayList<ParkingSpot> = ArrayList()
    private val spotsList  = Utils.getParkingSpots()

    //Int - ticketId
    private val activeTicketHashMap = HashMap<Int,Ticket>()

    /*init {
        spots.add(ParkingSpot(id = "B1", vehicleType = VehicleType.BIKE, isOccupied = false))
        spots.add(ParkingSpot(id = "B2", vehicleType = VehicleType.BIKE, isOccupied = false))
        spots.add(ParkingSpot(id = "C1", vehicleType = VehicleType.CAR, isOccupied = false))
        spots.add(ParkingSpot(id = "C2", vehicleType = VehicleType.CAR, isOccupied = false))
    }*/

    fun parkVehicle(vehicleNumber:String, vehicleType: VehicleType) {

        val vehicle = Vehicle(vehicleNumber = vehicleNumber, vehicleType = vehicleType)

        val spotIdx = spotsList.indexOfFirst { it.vehicleType == vehicle.vehicleType && it.isOccupied.not() }

        if (spotIdx != -1){
            val oldSpot = spotsList.get(spotIdx)
            val updatedSpot = oldSpot.copy(isOccupied = true)

            //Replaces the element at the specified position in this list with the specified element.
            spotsList.set(spotIdx, updatedSpot)

            val ticketObj = Ticket(vehicle = vehicle, spot = updatedSpot)
            activeTicketHashMap.put(ticketObj.ticketId, value = ticketObj)

            println("Parked ${vehicle} at spot ${updatedSpot.id} with ticketId ${ticketObj.ticketId} with time ${ticketObj.timeStamp}")
        }
        else{
            println("No available slot for vehicleType ${vehicle.vehicleType}")
        }
    }

    fun unparkVehicle(ticketId: Int){

        val ticket = activeTicketHashMap.get(ticketId)

        if (ticket != null) {
            val updatedTicket = ticket.spot.copy(isOccupied = false)
            calculateParkingFare(ticketId = ticketId)
            activeTicketHashMap.remove(ticketId)
            println("Unparked vehicle ${ticket.vehicle.vehicleNumber} at spot ${updatedTicket.id}")
        }
        else{
            println("Invalid ticket ID")
        }
    }

    private fun calculateParkingFare(ticketId: Int) {
        val basePrice = 10
        val parkedTime = activeTicketHashMap.get(ticketId)?.timeStamp

        if (parkedTime != null) {
            //Think of How many seconds have passed since parkedTime? | If more than 5 sec, charge more.
            //if (parkedTime.plusSeconds(5) < LocalDateTime.now()) println("Parking fare for vehicle number ${activeTicket.get(ticketId)?.vehicle?.vehicleNumber} Rs. ${basePrice + 10}")

            val duration = Duration.between(parkedTime, LocalDateTime.now()).toSeconds()
            if (duration > 5){
                println("Parking fare for vehicle number ${activeTicketHashMap.get(ticketId)?.vehicle?.vehicleNumber} Rs. ${basePrice + 10}")
            }
            else{
                println("Parking fare for vehicle number ${activeTicketHashMap.get(ticketId)?.vehicle?.vehicleNumber} Rs. ${basePrice}")
            }
        }
    }
}