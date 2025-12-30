package com.corpevent.corpevent.repos;

import com.corpevent.corpevent.model.ValidatorHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ValidatorHierarchyRepository extends JpaRepository<ValidatorHierarchy,Long> {
    Optional<ValidatorHierarchy> findByExecId(Long execId);

    List<ValidatorHierarchy> findBySefId(Long sefId);

    @Query("""
   SELECT vh.exec.id
   FROM ValidatorHierarchy vh
   WHERE vh.sef.id = :sefId
""")
    List<Long> findExecIdsBySef(@Param("sefId") Long sefId);


}
