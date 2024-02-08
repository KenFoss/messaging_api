package com.example.football_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
class VideoDatabase {
	private List<String> videoList;

	public VideoDatabase() {
		// Initialize the video list (replace this with actual video data)
		videoList = new ArrayList<>();
		videoList.add("Video 1");
		videoList.add("Video 2");
		videoList.add("Video 3");
	}

	public List<String> getVideoList() {
		return videoList;
	}
}

@Component
class VideoLocator {
	private VideoDatabase videoDatabase;

	@Autowired
	public VideoLocator(VideoDatabase videoDatabase) {
		this.videoDatabase = videoDatabase;
	}

	public String findVideo(String query) {
		// Replace this with your actual video search logic
		List<String> videos = videoDatabase.getVideoList();
		for (String video : videos) {
			if (video.contains(query)) {
				return video;
			}
		}
		return "Video not found";
	}
}

@SpringBootApplication
public class FootballApiApplication {

	public static void main(String[] args) {
		// Run the Spring Boot application and get the application context
		ConfigurableApplicationContext context = SpringApplication.run(FootballApiApplication.class, args);

		// Retrieve the VideoLocator bean from the context
		VideoLocator videoLocator = context.getBean(VideoLocator.class);

		// Query for a video
		String query = "Video 2";
		String result = videoLocator.findVideo(query);

		// Display the result
		System.out.println("Result: " + result);

		// Close the application context
		context.close();
	}

}
