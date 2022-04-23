package com.tqs.covid19Service.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Data
@Entity
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    private String continent;

    private String country;

    private int population;

    private String new_cases;

    private int active_cases;

    private int critical_cases;

    private int recovered_cases;

    private String pop1m_cases;

    private int total_cases;

    private String new_deaths;

    private String pop1m_deaths;

    private int total_deaths;

    private String pop1m_tests;

    private int total_tests;

    private String day; //Date??

    private String time; //Time??

    public Statistic() {
    }

    public Statistic(String continent, String country, int population, String new_cases, int active_cases,
            int critical_cases, int recovered_cases, String pop1m_cases, int total_cases, String new_deaths,
            String pop1m_deaths, int total_deaths, String pop1m_tests, int total_tests, String day, String time) {
        this.continent = continent;
        this.country = country;
        this.population = population;
        this.new_cases = new_cases;
        this.active_cases = active_cases;
        this.critical_cases = critical_cases;
        this.recovered_cases = recovered_cases;
        this.pop1m_cases = pop1m_cases;
        this.total_cases = total_cases;
        this.new_deaths = new_deaths;
        this.pop1m_deaths = pop1m_deaths;
        this.total_deaths = total_deaths;
        this.pop1m_tests = pop1m_tests;
        this.total_tests = total_tests;
        this.day = day;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(String new_cases) {
        this.new_cases = new_cases;
    }

    public int getActive_cases() {
        return active_cases;
    }

    public void setActive_cases(int active_cases) {
        this.active_cases = active_cases;
    }

    public int getCritical_cases() {
        return critical_cases;
    }

    public void setCritical_cases(int critical_cases) {
        this.critical_cases = critical_cases;
    }

    public int getRecovered_cases() {
        return recovered_cases;
    }

    public void setRecovered_cases(int recovered_cases) {
        this.recovered_cases = recovered_cases;
    }

    public String getPop1m_cases() {
        return pop1m_cases;
    }

    public void setPop1m_cases(String pop1m_cases) {
        this.pop1m_cases = pop1m_cases;
    }

    public int getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(int total_cases) {
        this.total_cases = total_cases;
    }

    public String getNew_deaths() {
        return new_deaths;
    }

    public void setNew_deaths(String new_deaths) {
        this.new_deaths = new_deaths;
    }

    public String getPop1m_deaths() {
        return pop1m_deaths;
    }

    public void setPop1m_deaths(String pop1m_deaths) {
        this.pop1m_deaths = pop1m_deaths;
    }

    public int getTotal_deaths() {
        return total_deaths;
    }

    public void setTotal_deaths(int total_deaths) {
        this.total_deaths = total_deaths;
    }

    public String getPop1m_tests() {
        return pop1m_tests;
    }

    public void setPop1m_tests(String pop1m_tests) {
        this.pop1m_tests = pop1m_tests;
    }

    public int getTotal_tests() {
        return total_tests;
    }

    public void setTotal_tests(int total_tests) {
        this.total_tests = total_tests;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Statistic [active_cases=" + active_cases + ", continent=" + continent + ", country=" + country
                + ", critical_cases=" + critical_cases + ", day=" + day + ", id=" + id + ", new_cases=" + new_cases
                + ", new_deaths=" + new_deaths + ", pop1m_cases=" + pop1m_cases + ", pop1m_deaths=" + pop1m_deaths
                + ", pop1m_tests=" + pop1m_tests + ", population=" + population + ", recovered_cases=" + recovered_cases
                + ", time=" + time + ", total_cases=" + total_cases + ", total_deaths=" + total_deaths
                + ", total_tests=" + total_tests + "]";
    }

    
}
