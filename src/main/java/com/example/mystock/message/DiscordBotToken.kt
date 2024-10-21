package com.example.mystock.message

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DiscordBotToken {

    @Value("\${discord.bot.token}")
    lateinit var discordBotToken: String
}
