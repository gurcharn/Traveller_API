package app.places

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Place {
    @Id
    private String placeId
    public String name
    public String address
    public String icon
    public float rating

    Place() {}

    Place(String placeId, String name, String address, String icon, float rating) {
        this.placeId = placeId
        this.name = name
        this.address = address
        this.icon = icon
        this.rating = rating
    }

    String getPlaceId() {
        return placeId
    }

    void setPlaceId(String placeId) {
        this.placeId = placeId
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getAddress() {
        return address
    }

    void setAddress(String address) {
        this.address = address
    }

    String getIcon() {
        return icon
    }

    void setIcon(String icon) {
        this.icon = icon
    }

    float getRating() {
        return rating
    }

    void setRating(float rating) {
        this.rating = rating
    }

    @Override
    boolean equals (Object o) {
        if(this==null && o==null) return true
        if(this!=null && o==null) return false
        if(this==null && o!=null) return false
        if(!(o instanceof Place)) return false
        Place place=(Place) o
        return (this.getPlaceId() == place.getPlaceId())
    }

    /**
     * Method to get hash of object
     * @return int
     */
    int hashCode() {
        return (placeId != null ? placeId.hashCode() : 0)
    }
}
