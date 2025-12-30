package com.corpevent.corpevent.repos;

import com.corpevent.corpevent.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    @Query("SELECT e FROM Event e WHERE e.eventDate >= CURRENT_TIMESTAMP")
    List<Event> findUpcomingEvents();


    @Query("SELECT COUNT(r) FROM Request r WHERE r.event.id = :eventId AND r.status = 'L3_ADMIS'")
    int countApprovedRequestsByEvent(Long eventId);
}
