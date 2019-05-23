package app.places

import com.google.maps.GeoApiContext
import com.google.maps.NearbySearchRequest
import com.google.maps.PlacesApi
import com.google.maps.TextSearchRequest
import com.google.maps.model.LatLng
import com.google.maps.model.PlacesSearchResponse
import com.google.maps.model.PriceLevel
import com.google.maps.model.RankBy
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GeoPlacesService {

    @Value('${google.places.api.key}')
    private String googleAPIkey
    private GeoApiContext geoApiContext

    Map<String, PlacesSearchResponse> getPlacesByTextSearch(PlaceRequest placeRequest){
        Map<String, PlacesSearchResponse> placesMap = new HashMap<String, PlacesSearchResponse>()

        for(String interest : placeRequest.getInterests()){
            String query = interest + " in " + placeRequest.getPlace()
            PlacesSearchResponse queryResult = this.getPlacesByTextSearchQuery(query)
            if(!queryResult.results.toList().isEmpty())
                placesMap.put(interest, queryResult)
        }

        return placesMap
    }

    Map<String, PlacesSearchResponse> getPlacesNearby(PlaceRequest placeRequest){
        Map<String, PlacesSearchResponse> placesMap = new HashMap<String, PlacesSearchResponse>()

        for(String interest : placeRequest.getInterests()){
            PlacesSearchResponse queryResult = this.getPlacesNearbyQuery(placeRequest.getLocationLat(), placeRequest.getLocationLng(), interest)
            if(!queryResult.results.toList().isEmpty())
                placesMap.put(interest, queryResult)
        }

        return placesMap
    }

    private PlacesSearchResponse getPlacesByTextSearchQuery(String query){
        return new TextSearchRequest(getContext()).query(query).await()
    }

    private PlacesSearchResponse getPlacesNearbyQuery(double lat, double lng, String keyword){
        return PlacesApi.nearbySearchQuery(getContext(), new LatLng(lat, lng)).radius(10000).keyword(keyword).await()
    }

    private GeoApiContext getContext(){
        if(geoApiContext == null)
            this.geoApiContext = new GeoApiContext.Builder().apiKey(googleAPIkey).build()

        return this.geoApiContext
    }
}
