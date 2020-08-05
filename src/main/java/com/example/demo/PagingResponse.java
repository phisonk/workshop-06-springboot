package com.example.demo;

import java.util.List;

public class PagingResponse {
    private List<UsersResponse> usersResponses;
    private int page;
    private int itemPerPage;

    public PagingResponse( int page, int itemPerPage) {
        this.page = page;
        this.itemPerPage = itemPerPage;
    }

    public List<UsersResponse> getUsersResponses() {
        return usersResponses;
    }

    public void setUsersResponses(List<UsersResponse> usersResponses) {
        this.usersResponses = usersResponses;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }
}
