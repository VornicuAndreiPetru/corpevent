package com.corpevent.corpevent.repos;

import com.corpevent.corpevent.enums.RequestStatus;
import com.corpevent.corpevent.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {

    List<Request> findByStatus(RequestStatus status);

    List<Request> findByValidatorExecId(Long execId);

    @Query("SELECT r FROM Request r WHERE r.validatorExec.id IN :execIds")
    List<Request> findAllByExecTeam(List<Long> execIds);

    @Query("SELECT r FROM Request r WHERE r.status IN ('L3_ADMIS','L4_RESPINS')")
    List<Request> findFinalizedRequests();

    List<Request> findByStatusAndValidatorExec_Id(RequestStatus status, Long execId);


    long countByEventIdAndStatus(Long eventId, RequestStatus status);


    @Query("""
    SELECT r FROM Request r
    WHERE r.status = :status
        AND (
            r.validatorSef.id = :sefId
            OR r.validatorExec.id IN :execIds
        )
""")
    List<Request> findForSefAndExecs(
            @Param("status") RequestStatus status,
            @Param("sefId") Long sefId,
            @Param("execIds") List<Long> execIds
    );


}
