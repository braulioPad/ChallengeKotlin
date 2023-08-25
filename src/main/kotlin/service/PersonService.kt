package service

import dto.PopularFollowerDTO
import dto.ResponseDto
import net.bytebuddy.asm.Advice.AllArguments
import org.springframework.stereotype.Service
import repository.FollowerRepository
import repository.MessageRepository
import repository.PersonRepository

@Service
class PersonService(
     val personRepository: PersonRepository,
     val messageRepository: MessageRepository,
     val followerRepository: FollowerRepository
)  {

     fun findAllMessagesByPersonId(personName: String, word: String?): ResponseDto<String> {
        val response = ResponseDto<String>(0,"",null)

        val person = personRepository.findByName(personName).orElse(null)

        if (person != null) {
            val result = messageRepository.findAllMessagesByPersonIdAndSearchWord(person.id, word)
            if (result.isNotEmpty()) {
                response.status = 200
                response.message = "Data retrieved successfully"
               // response.data = result.map { it.content }
                return response
            } else {
                response.status = 200
                response.message = "No Data found"
                response.data = emptyList()
                return response
            }
        }

        response.status = 404
        response.message = "No user found"
        response.data = emptyList()
        return response
    }

     fun popularsFollower(): ResponseDto<PopularFollowerDTO> {
        val response = ResponseDto<PopularFollowerDTO>(0,"",null)
        val result = personRepository.findAllUsersWithMostPopularFollower()
        val followers = mutableListOf<PopularFollowerDTO>()

        for (i in result.indices) {
            for (j in result.indices) {
                if (i != j) {
                   checkNotNull(followerRepository.findFollowerByPersonIdAndFollowerId(
                       result[i].getPersonId(),
                       result[j].getPersonId()
                   )) {
                        val follower = PopularFollowerDTO(
                            result[i].getPersonName(),
                            result[j].getPersonName(),
                            result[i].getFollowerCount()
                        )
                        followers.add(follower)
                    }
                    break
                }
            }
        }

        response.status = 200
        response.message = "Data retrieved successfully"
        response.data = followers
        return response
    }
}
