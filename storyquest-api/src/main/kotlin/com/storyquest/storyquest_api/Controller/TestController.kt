package com.storyquest.storyquest_api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource
import java.util.*

@RestController
class TestController {
    
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate
    
    @GetMapping("/api/test")
    fun testEndpoint(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result["message"] = "Hello from StoryQuest API!"
        
        // Test database connection
        try {
            val storyNodes = jdbcTemplate.queryForList("SELECT * FROM story_nodes")
            result["databaseConnected"] = true
            result["storyNodeCount"] = storyNodes.size
            result["storyNodes"] = storyNodes
        } catch (e: Exception) {
            result["databaseConnected"] = false
            result["error"] = e.message ?: "Unknown error"
        }
        
        return result
    }
}
