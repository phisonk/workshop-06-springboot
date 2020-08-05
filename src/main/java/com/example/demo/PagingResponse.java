package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"page","item_per_page","users"})
public class PagingResponse {
    @JsonProperty("users")
    private List<UsersResponse> usersResponses;
    private int page;
    @JsonProperty("item_per_page")
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
