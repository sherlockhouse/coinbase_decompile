package com.coinbase.v1.entity;

import java.io.Serializable;

public class ContactNode implements Serializable {
    private static final long serialVersionUID = 642700372938561693L;
    private Contact _contact;

    public Contact getContact() {
        return this._contact;
    }

    public void setContact(Contact contact) {
        this._contact = contact;
    }
}
