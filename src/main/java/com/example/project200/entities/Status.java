package com.example.project200.entities;

import lombok.Getter;

@Getter
public enum Status {
    PENDING("Pending"),
    REJECTED("Rejected"),
    SUCCESS("Success"),
    NOTIFY("Notify"),
    DONE("Done");

    private final String value;

    Status(String value) {
        this.value = value;
    }

}
