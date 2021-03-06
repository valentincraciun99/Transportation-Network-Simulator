package model;

import java.time.LocalDate;
import java.util.Date;

public class Subscription {
    Integer id;
    LocalDate endDate;
    //java.sql.Date.valueOf( localDate );  sqlDate.toLocalDate();

    public Subscription(Integer id, LocalDate endDate) {
        this.id = id;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getEndDate() {
        return endDate;

    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
