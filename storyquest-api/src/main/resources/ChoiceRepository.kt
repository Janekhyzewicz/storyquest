package com.storyquest.api.repository

import com.storyquest.api.model.Choice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChoiceRepository : JpaRepository<Choice, Long>