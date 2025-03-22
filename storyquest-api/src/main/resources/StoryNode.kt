package com.storyquest.api.model

import jakarta.persistence.*

@Entity
@Table(name = "story_nodes")
data class StoryNode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    val title: String,
    
    @Column(nullable = false, columnDefinition = "TEXT")
    val description: String,
    
    val backgroundImage: String?,
    
    @OneToMany(mappedBy = "storyNode", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val choices: MutableList<Choice> = mutableListOf()
)