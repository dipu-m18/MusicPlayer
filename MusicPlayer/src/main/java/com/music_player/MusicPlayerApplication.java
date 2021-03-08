package com.music_player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"classpath:messages.properties"})
public class MusicPlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicPlayerApplication.class, args);
	}

}
