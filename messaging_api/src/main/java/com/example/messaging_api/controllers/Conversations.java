package com.example.messaging_api.controllers;

import com.example.messaging_api.models.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messaging")
@CrossOrigin(origins = "*")
public class Conversations {

    private final JdbcTemplate jdbcTemplate;
    private int user;
    //

    public Conversations(@Autowired JdbcTemplate jdbcTemplateDependency){
        jdbcTemplate = jdbcTemplateDependency;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<Map<String, Object>> sendMessage(@RequestBody Message message) {
        try {
            String sql = "INSERT INTO \"Messages\" (\"chatId\", \"userId\", \"time\", \"message\") VALUES (?, ?, ?, ?)";

            jdbcTemplate.update(sql,
                    message.chatId(), message.userId(),
                    Timestamp.valueOf(LocalDateTime.now()), message.messageContent());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Message added successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Internal Server Error");
            e.printStackTrace(); // Log the exception details

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userChats")
    public ResponseEntity<Object> getChats(@RequestParam("userId") int userId) {
        String userConversationsQuery =
                "SELECT m.* FROM \"Messages\" \"m\" " +
                        "JOIN \"user_chats\" uc ON \"m\".\"chatId\" = ANY(\"uc\".\"chatids\") " +
                        "WHERE uc.\"userId\" = ?";

        List<Map<String, Object>> resultList = jdbcTemplate.query(userConversationsQuery, (resultSet, rowNum) -> {
            try {
                HashMap<String, Object> rowMap = new HashMap<>();
                rowMap.put("chatId", resultSet.getInt("chatId"));
                rowMap.put("userId", resultSet.getInt("userId"));
                rowMap.put("time", resultSet.getTimestamp("time"));
                rowMap.put("message", resultSet.getString("message"));
                rowMap.put("messageId", resultSet.getInt("messageId"));
                return rowMap;
            } catch (SQLException e) {
                throw new RuntimeException("Error converting ResultSet to Map", e);
            }
        }, userId);

        try {
            return ResponseEntity.ok(resultList);
        } catch (Exception e) {
            return new ResponseEntity<>("Error converting ResultSet to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
