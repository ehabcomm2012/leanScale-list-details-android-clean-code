package com.leanscale.listdetails.domain.models

import java.io.Serializable

data class GamesListResponse(
    val results: List<Games>?
)

data class Games(
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
