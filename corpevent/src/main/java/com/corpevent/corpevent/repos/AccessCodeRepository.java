package com.corpevent.corpevent.repos;

import com.corpevent.corpevent.model.AccessCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessCodeRepository extends JpaRepository<AccessCode, Long> {

    boolean existsByCode(String code);
}
