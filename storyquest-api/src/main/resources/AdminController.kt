package com.storyquest.api.controller

import com.storyquest.api.model.Choice
import com.storyquest.api.model.StoryNode
import com.storyquest.api.repository.ChoiceRepository
import com.storyquest.api.repository.StoryNodeRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val storyNodeRepository: StoryNodeRepository,
    private val choiceRepository: ChoiceRepository
) {
    
    @GetMapping("/nodes")
    fun getAllNodes(): List<StoryNode> {
        return storyNodeRepository.findAll()
    }
    
    @PostMapping("/nodes")
    fun createNode(@RequestBody node: StoryNode): StoryNode {
        return storyNodeRepository.save(node)
    }
    
    @PutMapping("/nodes/{id}")
    fun updateNode(@PathVariable id: Long, @RequestBody node: StoryNode): StoryNode {
        val existingNode = storyNodeRepository.findById(id)
            .orElseThrow { RuntimeException("Node not found") }
        
        val updatedNode = existingNode.copy(
            title = node.title,
            description = node.description,
            backgroundImage = node.backgroundImage
        )
        
        return storyNodeRepository.save(updatedNode)
    }
    
    @PostMapping("/nodes/{nodeId}/choices")
    fun addChoice(
        @PathVariable nodeId: Long,
        @RequestBody choice: Choice
    ): Choice {
        val node = storyNodeRepository.findById(nodeId)
            .orElseThrow { RuntimeException("Node not found") }
        
        val newChoice = Choice(
            text = choice.text,
            storyNode = node,
            targetNodeId = choice.targetNodeId
        )
        
        return choiceRepository.save(newChoice)
    }
}