package com.storyquest.api.repository

import com.storyquest.api.model.StoryNode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoryNodeRepository : JpaRepository<StoryNode, Long>