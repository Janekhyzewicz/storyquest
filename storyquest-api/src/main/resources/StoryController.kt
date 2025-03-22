package com.storyquest.api.controller

import com.storyquest.api.model.StoryNode
import com.storyquest.api.service.StoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/story")
class StoryController(private val storyService: StoryService) {
    
    @GetMapping("/nodes/{nodeId}")
    fun getNode(@PathVariable nodeId: Long): StoryNode {
        return storyService.getStoryNode(nodeId)
    }
    
    @GetMapping("/users/{userId}/current")
    fun getCurrentNode(@PathVariable userId: String): StoryNode {
        return storyService.getCurrentNodeForUser(userId)
    }
    
    @PostMapping("/users/{userId}/choices/{choiceId}")
    fun makeChoice(
        @PathVariable userId: String,
        @PathVariable choiceId: Long
    ): StoryNode {
        return storyService.makeChoice(userId, choiceId)
    }
    
    @PostMapping("/users/{userId}/reset")
    fun resetProgress(@PathVariable userId: String) {
        storyService.resetProgress(userId)
    }
}