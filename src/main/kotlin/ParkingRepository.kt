package org.example

import java.time.LocalDateTime

class ParkingRepository {

    private val spots : ArrayList<ParkingLot> = ArrayList()
    private val activeTicket = HashMap<Int,Ticket>()

    init {
        spots.add(ParkingLot(id = "B1", vehicleType = VehicleType.BIKE, isOccupied = false))
        spots.add(ParkingLot(id = "B2", vehicleType = VehicleType.BIKE, isOccupied = false))
        spots.add(ParkingLot(id = "C1", vehicleType = VehicleType.CAR, isOccupied = false))
        spots.add(ParkingLot(id = "C2", vehicleType = VehicleType.CAR, isOccupied = false))
    }

    fun parkVehicle(vehicleNumber:String, vehicleType: VehicleType) {

        val vehicle = Vehicle(vehicleNumber = vehicleNumber, vehicleType = vehicleType)

        val spotIdx = spots.indexOfFirst { it.vehicleType == vehicle.vehicleType && it.isOccupied.not() }

        if (spotIdx != -1){
            val oldSpot = spots.get(spotIdx)
            val updatedSpot = oldSpot.copy(isOccupied = true)

            //Replaces the element at the specified position in this list with the specified element.
            spots.set(spotIdx, updatedSpot)

            val ticketObj = Ticket(vehicle = vehicle, spot = updatedSpot)
            activeTicket.put(ticketObj.ticketId, value = ticketObj)

            println("Parked ${vehicle} at spot ${updatedSpot.id} with ticketId ${ticketObj.ticketId} with time ${ticketObj.timeStamp}")
        }
        else{
            println("No available slot for vehicleType ${vehicle.vehicleType}")
        }
    }

    fun unparkVehicle(ticketId: Int){

        val ticket = activeTicket.get(ticketId)

        if (ticket!=null){
            val updatedTicket = ticket.spot.copy(isOccupied = false)
            calculateParkingFare(ticketId = ticketId)
            activeTicket.remove(ticketId)
            println("Unparked vehicle ${ticket.vehicle.vehicleNumber} at spot ${updatedTicket.id}")
        }
        else{
            println("Invalid ticket ID")
        }
    }

    private fun calculateParkingFare(ticketId: Int) {
        val basePrice = 10
        val parkedTime = activeTicket.get(ticketId)?.timeStamp

        if (parkedTime != null) {
            //Think of How many seconds have passed since parkedTime? | If more than 5 sec, charge more.
            if (parkedTime.plusSeconds(5) < LocalDateTime.now()){
                println("Parking fare for vehicle number ${activeTicket.get(ticketId)?.vehicle?.vehicleNumber} Rs. ${basePrice + 10}")
            }
            else{
                println("Parking fare for vehicle number ${activeTicket.get(ticketId)?.vehicle?.vehicleNumber} Rs. ${basePrice}")
            }
        }
    }
}