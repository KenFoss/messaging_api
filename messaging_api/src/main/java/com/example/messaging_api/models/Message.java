package com.example.messaging_api.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record Message(int chatId, int userId, Timestamp time, String messageContent) {

    // Constructor with a custom logic (setting the time)
    public Message(int chatId, int userId, String messageContent) {
        this(chatId, userId, Timestamp.valueOf(LocalDateTime.now()), messageContent);
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }


}

