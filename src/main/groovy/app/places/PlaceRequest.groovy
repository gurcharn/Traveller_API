package app.places

class PlaceRequest {

    private String place
    private double locationLat
    private double locationLng
    private List<String> interests

    PlaceRequest() {}

    PlaceRequest(String place, double locationLat, double locationLng, List<String> interests) {
        this.place = place
        this.locationLat = locationLat
        this.locationLng = locationLng
        this.interests = interests
    }

    String getPlace() {
        return place
    }

    void setPlace(String place) {
        this.place = place
    }

    List<String> getInterests() {
        return interests
    }

    void setInterests(List<String> interests) {
        this.interests = interests
    }

    double getLocationLat() {
        return locationLat
    }

    void setLocationLat(double locationLat) {
        this.locationLat = locationLat
    }

    double getLocationLng() {
        return locationLng
    }

    void setLocationLng(double locationLng) {
        this.locationLng = locationLng
    }

    boolean isValid(){

        String place = (getPlace() != null ? getPlace() : "").trim()

        if(place == null)
            return false
        else if(place.isEmpty())
            return false
        else if(place.equals(""))
            return false
        else if(place.equals("null"))
            return false
        else if(place.equals("None"))
            return false
        else if(place.equals("none"))
            return false
        else if(place.length() <= 0)
            return false
        else
            return true
    }
}
