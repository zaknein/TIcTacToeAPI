package com.zaknein.TicTacToeAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.zaknein.TicTacToeAPI.config.RsaKeyProperties;

@EnableConfigurationProperties({RsaKeyProperties.class})
@SpringBootApplication
public class TicTacToeApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(TicTacToeApiApplication.class, args);
	}

}
