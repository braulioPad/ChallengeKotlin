package repository

import entity.Follower
import entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FollowerRepository : JpaRepository<Follower, Long> {

    @Query("SELECT f.followerPerson FROM Follower f WHERE f.person.id = :personId")
    fun findByFollowers(@Param("personId") personId: Long): List<Person>

    @Query("SELECT f FROM Follower f WHERE f.person.id = :personId AND f.followerPerson.id = :followerId")
    fun findFollowerByPersonIdAndFollowerId(@Param("personId") personId: Long, @Param("followerId") followerId: Long): Follower
}