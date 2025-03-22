package com.storyquest.api.model

import jakarta.persistence.*

@Entity
@Table(name = "choices")
data class Choice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    val text: String,
    
    @ManyToOne
    @JoinColumn(name = "story_node_id")
    val storyNode: StoryNode,
    
    @Column(name = "target_node_id")
    val targetNodeId: Long
)