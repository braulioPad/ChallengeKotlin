package repository

import entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import projection.PopularFollowerProjection
import java.util.*

@Repository
interface PersonRepository : JpaRepository<Person, Long> {

    fun findByName(name: String): Optional<Person>

    @Query(nativeQuery = true, value = "SELECT p.id AS personId, p.name AS personName, COUNT(f.id) AS followerCount "
            + "FROM people p " + "LEFT JOIN followers f ON p.id = f.person_id " + "GROUP BY p.id, p.name "
            + "ORDER BY followerCount DESC")
    fun findAllUsersWithMostPopularFollower(): List<PopularFollowerProjection>
}