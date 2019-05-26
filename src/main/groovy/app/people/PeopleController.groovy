package app.people

import app.trip.Trip
import app.userProfile.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/secure/people")
class PeopleController {

    @Autowired
    private PeopleService peopleService

    @GetMapping
    Set<User> getPeopleSuggestions(@RequestParam(required = true) String tripId){
        return peopleService.searchPeopleSuggestion(tripId)
    }
}
