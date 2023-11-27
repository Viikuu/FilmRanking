package com.example.filmRanking.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormattedResponse {
    private int status;
    private String message;
    private String data;

    static public FormattedResponse settupFormattedResponse(int status, String message, String data) {
        return new FormattedResponse(status, message, data);
    }
}
