package org.example.xqy1._026_silver_residence.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@RequestMapping("/ai")
@RestController
public class AIController {

    private final ChatClient chatClient;

    private static final Pattern NO_THINK_PATTERN = Pattern.compile("/no_think$");

    @PostMapping(value = "/chat" , produces = "text/event-stream")
    public Flux<String> chat(@RequestBody String request) {
        // 1. 清理请求参数（移除/no_think）
        String cleanPrompt = NO_THINK_PATTERN.matcher(request.trim()).replaceAll("");

        // 2. 构建流式请求（基础参数从 yml 来，/no_think 时临时降低温度）
        var promptSpec = chatClient.prompt()
                .user(cleanPrompt);

        // 如果是/no_think请求，临时覆盖温度为 0（最快响应）
        if (request.contains("/no_think")) {
            promptSpec.options(ChatOptions.builder()
                    .temperature(0.0) // 极速模式
                    .build());
        }
        return promptSpec
                .stream()
                .content()
                .map(content -> "data: " + content + "\n\n") // SSE 标准格式
                .timeout(java.time.Duration.ofSeconds(60)); // 超时保护

}
}
