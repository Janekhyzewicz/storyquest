package com.storyquest.api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.storyquest.api.model.StoryNode
import com.storyquest.api.model.UserProgress
import com.storyquest.api.repository.ChoiceRepository
import com.storyquest.api.repository.StoryNodeRepository
import com.storyquest.api.repository.UserProgressRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoryService(
    private val storyNodeRepository: StoryNodeRepository,
    private val choiceRepository: ChoiceRepository,
    private val userProgressRepository: UserProgressRepository,
    private val objectMapper: ObjectMapper
) {
    
    @Transactional
    fun getStoryNode(nodeId: Long): StoryNode {
        return storyNodeRepository.findById(nodeId)
            .orElseThrow { RuntimeException("Story node not found") }
    }
    
    @Transactional
    fun getCurrentNodeForUser(userId: String): StoryNode {
        var userProgress = userProgressRepository.findByUserId(userId)
        
        if (userProgress == null) {
            // New user, assign first node (ID 1)
            val firstNode = storyNodeRepository.findById(1L)
                .orElseThrow { RuntimeException("Starting node not found") }
            
            userProgress = UserProgress(
                userId = userId,
                currentNode = firstNode
            )
            userProgressRepository.save(userProgress)
        }
        
        return userProgress.currentNode
    }
    
    @Transactional
    fun makeChoice(userId: String, choiceId: Long): StoryNode {
        val choice = choiceRepository.findById(choiceId)
            .orElseThrow { RuntimeException("Choice not found") }
        
        val targetNode = storyNodeRepository.findById(choice.targetNodeId)
            .orElseThrow { RuntimeException("Target node not found") }
        
        val userProgress = userProgressRepository.findByUserId(userId)
            ?: throw RuntimeException("User progress not found")
        
        // Update user progress
        userProgress.currentNode = targetNode
        
        // Track visited nodes
        val visitedList = objectMapper.readValue(
            userProgress.visitedNodes, 
            Array<Long>::class.java
        ).toMutableList()
        
        if (!visitedList.contains(targetNode.id)) {
            visitedList.add(targetNode.id)
            userProgress.visitedNodes = objectMapper.writeValueAsString(visitedList)
        }
        
        userProgressRepository.save(userProgress)
        
        return targetNode
    }
    
    @Transactional
    fun resetProgress(userId: String) {
        val userProgress = userProgressRepository.findByUserId(userId)
            ?: return
        
        val firstNode = storyNodeRepository.findById(1L)
            .orElseThrow { RuntimeException("Starting node not found") }
        
        userProgress.currentNode = firstNode
        userProgress.visitedNodes = "[]"
        userProgressRepository.save(userProgress)
    }
}