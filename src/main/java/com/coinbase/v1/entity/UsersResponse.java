package com.coinbase.v1.entity;

import java.util.List;

public class UsersResponse extends Response {
    private static final long serialVersionUID = -2210197639875241944L;
    private List<UserNode> _users;

    public List<UserNode> getUsers() {
        return this._users;
    }

    public void setUsers(List<UserNode> users) {
        this._users = users;
    }
}
