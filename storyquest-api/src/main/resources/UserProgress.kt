package com.storyquest.api.model

import jakarta.persistence.*

@Entity
@Table(name = "user_progress")
data class UserProgress(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    val userId: String,
    
    @ManyToOne
    @JoinColumn(name = "current_node_id")
    var currentNode: StoryNode,
    
    @Column(columnDefinition = "jsonb")
    var visitedNodes: String = "[]"
)