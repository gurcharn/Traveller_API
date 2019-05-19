package app.trip

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Trip {

    @Id
    private String tripId
    private String userId
    private String place
    private Date arrival
    private Date departure

    Trip () {
    }

    Trip (String userId, String place, Date arrival, Date departure) {
        this.userId=userId
        this.place=place
        this.arrival=arrival
        this.departure=departure
    }

    String getTripId () {
        return tripId
    }

    String getUserId () {
        return userId
    }

    String getPlace () {
        return place
    }

    void setPlace (String place) {
        this.place=place
    }

    String getArrival () {
        return (arrival.getYear()+1900) + "-" + (arrival.getMonth()+1) + "-" + (arrival.getDate())
    }

    void setArrival (Date arrival) {
        this.arrival=arrival
    }

    String getDeparture () {
        return (departure.getYear()+1900) + "-" + (departure.getMonth()+1) + "-" + (departure.getDate())
    }

    void setDeparture (Date departure) {
        this.departure=departure
    }

    @Override
    boolean equals (Object o) {
        if(this==null && o==null) return true
        if(this!=null && o==null) return false
        if(this==null && o!=null) return false
        if(!(o instanceof Trip)) return false
        Trip trip=(Trip) o
        return Objects.equals(getUserId(), trip.getUserId())&&Objects.equals(getPlace(), trip.getPlace())&&Objects.equals(getArrival(), trip.getArrival())&&Objects.equals(getDeparture(), trip.getDeparture())
    }

    @Override
    int hashCode () {
        return Objects.hash(getUserId(), getPlace(), getArrival(), getDeparture())
    }
}

