package com.craterzone.ldesigndemo.modal;

/**
 * Created by user1 on 6/1/2015.
 */
public class Account {
    public String number;
    public String type;
    public String owner;

    public Account(String owner,String number, String type) {
        this.number = number;
        this.type = type;
        this.owner = owner;
    }

}
