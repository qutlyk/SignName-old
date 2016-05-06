package com.lihuanyu.signin.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by skyADMIN on 16/3/4.
 */
@Entity
@Table(name = "sign_list")
public class SignList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int yibanid;

    private String yibanname;
    private Timestamp signed_time;
    private String realname;



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

    public String getYibanname() {
        return yibanname;
    }

    public void setYibanname(String yibanname) {
        this.yibanname = yibanname;
    }

    public Timestamp getSigned_time() {
        return signed_time;
    }

    public void setSigned_time(Timestamp signed_time) {
        this.signed_time = signed_time;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
