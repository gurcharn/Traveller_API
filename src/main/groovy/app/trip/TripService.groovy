package app.trip

import org.springframework.data.mongodb.repository.MongoRepository;

interface TripService extends MongoRepository<Trip, String> {

    List<Trip> findByUserId(String userId)
    Trip findByTripId(String tripId)
}