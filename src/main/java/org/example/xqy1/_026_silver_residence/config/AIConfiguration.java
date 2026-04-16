package org.example.xqy1._026_silver_residence.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.model.tool.ToolCallingChatOptions;

@Configuration
@ComponentScan(basePackages = "org.example")
public class AIConfiguration {


    @Bean
    public ChatClient chatClient(OllamaChatModel model) {
        return ChatClient.builder(model)
                .build();
    }

}
