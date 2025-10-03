package com.gc.vertex.spring.ai.pgvector.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vertexai.embedding.VertexAiEmbeddingConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GcVertexSpringAIConfig {

    @Bean
    public VertexAiEmbeddingConnectionDetails vertexAiEmbeddingConnectionDetails() {
        return VertexAiEmbeddingConnectionDetails.builder()
                .projectId("gc-vertex-spring-ai-project") // Replace with your GCP project ID
                .location("us-central1") // Replace with your desired GCP region
                .build();
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        // Spring AI provides a ChatClient.Builder
        return ChatClient.builder(chatModel).build();
    }
}
