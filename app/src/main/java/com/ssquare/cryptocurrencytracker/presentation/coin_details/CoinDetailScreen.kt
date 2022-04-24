package com.ssquare.cryptocurrencytracker.presentation.coin_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.ssquare.cryptocurrencytracker.common.CustomLinearProgressBar
import com.ssquare.cryptocurrencytracker.presentation.coin_details.components.CoinTag
import com.ssquare.cryptocurrencytracker.presentation.coin_details.components.TeamListItem


@Preview
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)) {
                item {
                    Card(
                        elevation = 5.dp,
//                        border = BorderStroke(1.dp, color = Color.Yellow),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${coin.name} (${coin.symbol})",
                                    modifier = Modifier.weight(8f),
                                    style = MaterialTheme.typography.h2
                                )
                                Text(
                                    text = if (coin.isActive) "Active" else "Inactive",
                                    fontStyle = FontStyle.Italic,
                                    color = if (coin.isActive) Color.Green else Color.Red,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier
                                        .align(CenterVertically)
                                        .weight(2f)
                                )
                            }
                            Divider(modifier = Modifier.padding(horizontal = 20.dp))
                            Text(
                                text = "Rank: ${coin.rank}",
                                style = MaterialTheme.typography.h3,
                                modifier = Modifier.padding(bottom = 20.dp,start = 20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = coin.description, style = MaterialTheme.typography.body2,
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    if(!coin.team.isNullOrEmpty()){
                        Text(
                            text = "Team Members",
                            style = MaterialTheme.typography.h3
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMembers = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }
        if (state.isLoading) {
            CustomLinearProgressBar()
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
    }

}