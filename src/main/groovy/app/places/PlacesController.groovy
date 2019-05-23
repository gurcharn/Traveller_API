package app.places

import com.google.maps.model.PlacesSearchResponse
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
    private GeoPlacesService geoPlacesService

    @PostMapping("/textSearch")
    Map<String, PlacesSearchResponse> getPlacesByTextSearch(@RequestBody PlaceRequest placeRequest){
        if(placeRequest.isValid())
            return geoPlacesService.getPlacesByTextSearch(placeRequest)
        else
            throw  new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Invalid request for places")
    }

    @PostMapping("/nearby")
    Map<String, PlacesSearchResponse> getPlacesNearby(@RequestBody PlaceRequest placeRequest){
        if(placeRequest.isValid())
            return geoPlacesService.getPlacesNearby(placeRequest)
        else
            throw  new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Invalid request for places")
    }
}
