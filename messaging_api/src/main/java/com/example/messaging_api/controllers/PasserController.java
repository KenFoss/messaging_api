package com.example.messaging_api.controlers;

import com.example.messaging_api.models.Passer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/passer")
public class PasserController {

    private final JdbcTemplate jdbcTemplate;
//
    @Autowired
    public PasserController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addPlayer(@RequestBody Passer passer) {
        try {
            String sql = "INSERT INTO passing_stats (player_id, player, team_name, year, interceptions, touchdowns, ypa, attempts, position) " +
                    "VALUES (:playerId, :playerName, :teamName, :year, :interceptions, :touchdowns, :ypa, :attempts, :position) " +
                    "RETURNING *";

            Map<String, Object> result = jdbcTemplate.queryForMap(sql, passer.getData());

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Internal Server Error");
            e.printStackTrace(); // Log the exception details

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
