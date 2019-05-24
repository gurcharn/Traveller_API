package app.places

import com.google.gson.JsonObject
import com.google.maps.GeoApiContext
import com.google.maps.NearbySearchRequest
import com.google.maps.PlacesApi
import com.google.maps.TextSearchRequest
import com.google.maps.model.LatLng
import com.google.maps.model.PlacesSearchResponse
import com.google.maps.model.PlacesSearchResult
import com.google.maps.model.PriceLevel
import com.google.maps.model.RankBy
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import groovy.json.*

@Component
class GooglePlacesService {

    @Value('${google.places.api.key}')
    private String googleAPIkey
    private GeoApiContext geoApiContext

    Set<Place> getPlacesByTextSearch(PlaceRequest placeRequest){
        List<PlacesSearchResult> placesSearchResultList = new ArrayList<PlacesSearchResult>()

        for(String interest : placeRequest.getInterests()){
            String query = interest + " in " + placeRequest.getPlace()
            List<PlacesSearchResult> queryResult = this.getPlacesByTextSearchQuery(query).results.toList()
            if(!queryResult.isEmpty())
                placesSearchResultList.addAll(queryResult)
        }

        return toPlacesList(placesSearchResultList)
    }

    Set<Place> getPlacesNearby(PlaceRequest placeRequest){
        List<PlacesSearchResult> placesSearchResultList = new ArrayList<PlacesSearchResult>()

        for(String interest : placeRequest.getInterests()){
            List<PlacesSearchResult> queryResult = this.getPlacesNearbyQuery(placeRequest.getLocationLat(), placeRequest.getLocationLng(), interest).results.toList()
            if(!queryResult.isEmpty())
                placesSearchResultList.addAll(queryResult)
        }

        return toPlacesList(placesSearchResultList)
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

    private Set<Place> toPlacesList(List<PlacesSearchResult> placesSearchResults){
        Set<Place> placeList = new HashSet<Place>()

        for(PlacesSearchResult psr : placesSearchResults)
            placeList.add(new Place(psr.placeId, psr.name, psr.formattedAddress, psr.icon.toString(), psr.rating))

        return placeList
    }
}
