package com.ssquare.cryptocurrencytracker.domain.model

import com.ssquare.cryptocurrencytracker.data.remote.dto.TeamMembers

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val rank: Int,
    val symbol: String,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMembers>
)
