package model;

import java.util.Date;

public class Subscription {
    Integer id;
    Date  end_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEnd_date() {
        return end_date;

    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
