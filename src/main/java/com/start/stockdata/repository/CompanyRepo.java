package com.start.stockdata.repository;

import com.start.stockdata.identity.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends AbstractRemovableEntityRepository<Company>, JpaSpecificationExecutor<Company> {
    List<Company> findAllById(Long id);
}
