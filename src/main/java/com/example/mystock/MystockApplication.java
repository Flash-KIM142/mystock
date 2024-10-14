package com.example.mystock;

import com.example.mystock.message.DiscordBotToken;
import com.example.mystock.stock.StockBotListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.security.auth.login.LoginException;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class MystockApplication {

	public static void main(String[] args) throws LoginException {
		ApplicationContext context = SpringApplication.run(MystockApplication.class, args);
	}

	@Bean
	public JDA jda(DiscordBotToken discordBotToken, StockBotListener stockBotListener) throws Exception {
		String token = discordBotToken.getDiscordBotToken();

		return JDABuilder.createDefault(token)
				.setActivity(Activity.playing("waiting for a message..."))
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.addEventListeners(stockBotListener)
				.build()
				.awaitReady();
	}
}
