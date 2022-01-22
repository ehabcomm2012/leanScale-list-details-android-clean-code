package com.leanscale.listdetails.domain.models

data class GamesRequest(val key: String,
                       val pageSize: Int,
                        val page: Int)