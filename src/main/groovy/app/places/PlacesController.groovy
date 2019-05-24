package app.places

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException

@RestController
@RequestMapping("/secure/places")
class PlacesController {

    @Autowired
    private GooglePlacesService googlePlacesService

    @PostMapping("/textSearch")
    Set<Place> getPlacesByTextSearch(@RequestBody PlaceRequest[] placeRequest){
        if(placeRequest[0].isValid())
            return googlePlacesService.getPlacesByTextSearch(placeRequest[0])
        else
            throw  new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Invalid request for places")
    }

    @PostMapping("/nearby")
    Set<Place> getPlacesNearby(@RequestBody PlaceRequest[] placeRequest){
        if(placeRequest[0].isValid())
            return googlePlacesService.getPlacesNearby(placeRequest[0])
        else
            throw  new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Invalid request for places")
    }
}
