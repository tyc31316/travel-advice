import com.fasterxml.jackson.annotation.JsonProperty

data class ApiResponse(
    val result: List<MonthInfo>,
)

data class MonthInfo(
    val month: String,
    val reason: Reason,
)

enum class Reason {
    @JsonProperty("good weather")
    GOOD_WEATHER,

    @JsonProperty("less crowded")
    LESS_CROWDED,

    @JsonProperty("festival")
    FESTIVAL,
}
