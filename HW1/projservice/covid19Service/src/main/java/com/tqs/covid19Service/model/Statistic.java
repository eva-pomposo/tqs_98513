package com.tqs.covid19Service.model;

public class Statistic {
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

    private String day;

    private String time; 

    public Statistic(){}

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + active_cases;
        result = prime * result + ((continent == null) ? 0 : continent.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + critical_cases;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((new_cases == null) ? 0 : new_cases.hashCode());
        result = prime * result + ((new_deaths == null) ? 0 : new_deaths.hashCode());
        result = prime * result + ((pop1m_cases == null) ? 0 : pop1m_cases.hashCode());
        result = prime * result + ((pop1m_deaths == null) ? 0 : pop1m_deaths.hashCode());
        result = prime * result + ((pop1m_tests == null) ? 0 : pop1m_tests.hashCode());
        result = prime * result + population;
        result = prime * result + recovered_cases;
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + total_cases;
        result = prime * result + total_deaths;
        result = prime * result + total_tests;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Statistic other = (Statistic) obj;
        if (active_cases != other.active_cases)
            return false;
        if (continent == null) {
            if (other.continent != null)
                return false;
        } else if (!continent.equals(other.continent))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (critical_cases != other.critical_cases)
            return false;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (new_cases == null) {
            if (other.new_cases != null)
                return false;
        } else if (!new_cases.equals(other.new_cases))
            return false;
        if (new_deaths == null) {
            if (other.new_deaths != null)
                return false;
        } else if (!new_deaths.equals(other.new_deaths))
            return false;
        if (pop1m_cases == null) {
            if (other.pop1m_cases != null)
                return false;
        } else if (!pop1m_cases.equals(other.pop1m_cases))
            return false;
        if (pop1m_deaths == null) {
            if (other.pop1m_deaths != null)
                return false;
        } else if (!pop1m_deaths.equals(other.pop1m_deaths))
            return false;
        if (pop1m_tests == null) {
            if (other.pop1m_tests != null)
                return false;
        } else if (!pop1m_tests.equals(other.pop1m_tests))
            return false;
        if (population != other.population)
            return false;
        if (recovered_cases != other.recovered_cases)
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (total_cases != other.total_cases)
            return false;
        if (total_deaths != other.total_deaths)
            return false;
        if (total_tests != other.total_tests)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Statistic [active_cases=" + active_cases + ", continent=" + continent + ", country=" + country
                + ", critical_cases=" + critical_cases + ", day=" + day + ", new_cases=" + new_cases + ", new_deaths="
                + new_deaths + ", pop1m_cases=" + pop1m_cases + ", pop1m_deaths=" + pop1m_deaths + ", pop1m_tests="
                + pop1m_tests + ", population=" + population + ", recovered_cases=" + recovered_cases + ", time=" + time
                + ", total_cases=" + total_cases + ", total_deaths=" + total_deaths + ", total_tests=" + total_tests
                + "]";
    }

}
