package com.gc.vertex.spring.ai.pgvector.controller;

import com.gc.vertex.spring.ai.pgvector.service.RagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/add")
    public String addDoc(@RequestBody String text) {
        ragService.addDocument(text);
        return "Stored document in vector DB";
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String q) {
        return ragService.askQuestion(q);
    }
}
