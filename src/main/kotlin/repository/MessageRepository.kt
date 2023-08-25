package repository

import entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.person.id = :personId AND (:searchWord IS NULL OR m.content LIKE %:searchWord%)")
    fun findAllMessagesByPersonIdAndSearchWord(@Param("personId") personId: Long, @Param("searchWord") searchWord: String?): List<Message>

    @Query("SELECT m FROM Message m WHERE m.person.id = :personId")
    fun findAllMessagesByPersonId(@Param("personId") personId: Long): List<Message>
}