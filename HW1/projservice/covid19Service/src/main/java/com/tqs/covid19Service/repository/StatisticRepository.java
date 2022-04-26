package com.tqs.covid19Service.repository;

import org.springframework.stereotype.Repository;
import com.tqs.covid19Service.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    
}