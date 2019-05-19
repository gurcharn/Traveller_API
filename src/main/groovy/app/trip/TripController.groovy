package app.trip

import app.userProfile.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException


@RestController
@RequestMapping("/secure/trip")
class TripController {

    @Autowired
    private TripService tripService
    @Autowired
    private UserService userService

    @GetMapping
    List<Trip> getTrips(@RequestParam(required = true) String userId){
        return tripService.findByUserId(userId)
    }

    @PostMapping
    Trip addTrip(@RequestBody Trip trip){
        if(!isUserIdExist(trip.getUserId()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User doesn't exist")
        else if(isTripExist(trip))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Trip already exist")
        else if(!isTripValid(trip))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Not a valid trip")
        else
            return tripService.insert(trip)
    }

    @PatchMapping
    void updateTrip(@RequestBody Trip trip){
        if(!isUserIdExist(trip.getUserId()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User doesn't exist")
        else if(!isTripExist(trip))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Trip doesn't exist")
        else if(!isTripValid(trip) || !trip.getTripId())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Not a valid trip")
        else
            tripService.save(trip)
    }

    @DeleteMapping
    void deleteTrip(@RequestBody Trip trip){
        if(!isUserIdExist(trip.getUserId()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User doesn't exist")
        else if(!isTripExist(trip))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Trip doesn't exist")
        else if(!isTripValid(trip) || !trip.getTripId())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Not a valid trip")
        else
            tripService.delete(trip)
    }

    private boolean isTripValid(Trip trip){
        return trip && trip.getUserId() && trip.getArrival() && trip.getDeparture()
    }

    private boolean isTripExist(Trip trip){
        if(tripService.findByUserId(trip.getUserId()).contains(trip))
            return true
        else if(!trip.getTripId())
            return false
        else if(tripService.findByTripId(trip.getTripId()).getUserId().equals(trip.getUserId()))
            return true
        else
            return false
    }

    boolean isUserIdExist(String userId){
        return userService.findByUserId(userId)
    }
}