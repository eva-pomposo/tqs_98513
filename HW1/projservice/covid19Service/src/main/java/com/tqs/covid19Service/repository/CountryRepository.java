package com.tqs.covid19Service.repository;

import org.springframework.stereotype.Repository;
import com.tqs.covid19Service.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    
}
