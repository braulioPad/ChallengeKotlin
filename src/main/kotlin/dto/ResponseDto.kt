package dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

data class ResponseDto<T>(
    var status: Int,
    var message: String,
    @JsonInclude(Include.NON_NULL)
    var data: List<T>? = null
)