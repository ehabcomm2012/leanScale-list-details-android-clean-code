package com.leanscale.listdetails.domain.models

import java.io.Serializable

data class GamesListResponse(
    val count:Int,
    val results: List<Game>?
)

data class Game(
    val id: String,
    val name: String = "",
    val released: String?,
    val rating: Float,
    val background_image: String = "",
    val genres: List<Genre>?
) : Serializable


data class Genre(
    val id: String,
    val name: String = ""
) : Serializable
