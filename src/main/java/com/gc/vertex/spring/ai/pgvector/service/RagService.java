package com.gc.vertex.spring.ai.pgvector.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RagService {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    public RagService(VectorStore vectorStore, ChatClient chatClient) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClient;
    }

    public void addDocument(String text) {
        vectorStore.add(List.of(new Document(text)));
    }

    public String askQuestion(String question) {
        // 1. Retrieve relevant documents from pgvector
        List<Document> results = vectorStore.similaritySearch(question);

        String context = results.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));

        // 2. Ask VertexAI LLM with retrieved context
        return chatClient.prompt()
                .user("Use the following context to answer:\n" + context + "\n\nQuestion: " + question)
                .call()
                .content();
    }
}
