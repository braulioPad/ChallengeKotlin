package projection

interface PopularFollowerProjection {

    fun getPersonId(): Long

    fun getPersonName(): String

    fun getFollowerCount(): Long
}