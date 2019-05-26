package app.people

import app.trip.Trip
import app.trip.TripService
import app.userProfile.User
import app.userProfile.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PeopleService {

    @Autowired
    private UserService userService
    @Autowired
    private TripService tripService

    Set<User> searchPeopleSuggestion(String tripId){
        Set<User> suggestedPeople = new HashSet<User>()
        Trip trip = tripService.findByTripId(tripId)
        List<Trip> tripList = tripService.findByPlaceLikeIgnoreCase(trip.getPlace())

        for(Trip trip1 : tripList)
            if(trip.getUserId() != trip1.getUserId())
                if(isBetween(trip1.getArrivalDate(), trip.getArrivalDate(), trip.getDepartureDate()) || isBetween(trip1.getDepartureDate(), trip.getArrivalDate(), trip.getDepartureDate()))
                    suggestedPeople.add(userService.findByUserId(trip1.getUserId()))

        if(suggestedPeople == null)
            suggestedPeople.add(new User())
        else if(suggestedPeople.isEmpty())
            suggestedPeople.add(new User())

        return suggestedPeople
    }

    private boolean isBetween(Date date, Date arrival, Date departure) {
        return (date >= arrival || date <= departure)
    }
}
