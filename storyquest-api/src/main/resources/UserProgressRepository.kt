package com.storyquest.api.repository

import com.storyquest.api.model.UserProgress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProgressRepository : JpaRepository<UserProgress, Long> {
    fun findByUserId(userId: String): UserProgress?
}