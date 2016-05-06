package com.lihuanyu.signin.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by skyADMIN on 16/5/6.
 */
@Entity
@Table(name = "createlist")
public class CreateList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int yibanid;
    private String activityname;

    private Timestamp signed_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYibanid() {
        return yibanid;
    }

    public void setYibanid(int yibanid) {
        this.yibanid = yibanid;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    public Timestamp getSigned_time() {
        return signed_time;
    }

    public void setSigned_time(Timestamp signed_time) {
        this.signed_time = signed_time;
    }
}
