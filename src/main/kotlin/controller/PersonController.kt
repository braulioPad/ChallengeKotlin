package controller

import dto.PopularFollowerDTO
import dto.ResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.PersonService

@RestController
@RequestMapping("/users")
class PersonController( val personService: PersonService) {

    @GetMapping("/popular-follower")
    fun getAllUsersWithMostPopularFollower(): ResponseEntity<ResponseDto<PopularFollowerDTO>> {
        val response = null//personService.popularsFollower()
        return ResponseEntity.ok(response)
    }
}