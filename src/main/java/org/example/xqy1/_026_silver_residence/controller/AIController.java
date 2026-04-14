//package org.example.xqy1._026_silver_residence.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RequiredArgsConstructor
//@RequestMapping("/ai")
//@RestController
//public class AIController {
//
//    private final ChatClient chatClient;
//
//    @PostMapping("/chat")
//    public Map<String, String> chat(@RequestBody Map<String, String> request) {
//        String message = request.get("message");
//
//        // 调用Ollama模型获取响应
//        String response = chatClient.prompt()
//                .user(message)
//                .call()
//                .content();
//
//        // 构建返回结果
//        Map<String, String> result = new HashMap<>();
//        result.put("response", response);
//        return result;
//    }
//
//}
