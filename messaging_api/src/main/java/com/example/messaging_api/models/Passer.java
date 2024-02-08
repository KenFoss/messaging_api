package com.example.messaging_api.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

public class Passer {
    private static int playerId;
    private static String playerName;
    private static String teamName;
    private static int year;
    private static int interceptions;
    private static int touchdowns;
    private static double ypa;
    private static int attempts;
    private static String position;

    private static Map<String, Object> paramMap;

    @Autowired
    public Passer(@Qualifier("paramMap") HashMap<String, Object> paramMap,
                  int playerId, String playerName, String teamName,
                  int year, int interceptions, int touchdowns,
                  double ypa, int attempts, String position) {

        paramMap.put("playerId", playerId);
        paramMap.put("playerName", playerName);
        paramMap.put("teamName", teamName);
        paramMap.put("year", year);
        paramMap.put("interceptions", interceptions);
        paramMap.put("touchdowns", touchdowns);
        paramMap.put("ypa", ypa);
        paramMap.put("attempts", attempts);
        paramMap.put("position", position);
    }

    public Map<String, Object> getData() {
        return(paramMap);
    }


}
