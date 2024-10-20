package me.androidbox.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.core.presentation.ui.theme.BusbyCryptoTrackerTheme
import me.androidbox.core.presentation.ui.theme.greenBackground
import me.androidbox.crypto.presentation.models.DisplayableNumber

@Composable
fun PriceChange(
    percentChange: DisplayableNumber
) {
    val contentColor = if(percentChange.value < 0.0) MaterialTheme.colorScheme.onErrorContainer else Color.Green
    val containerColor = if(percentChange.value < 0.0) MaterialTheme.colorScheme.errorContainer else greenBackground
    val iconType = if(percentChange.value < 0.0) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100f))
            .background(color = containerColor)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically) {

        Icon(
            imageVector = iconType,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = "${percentChange.formatted} %",
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
@PreviewLightDark
@Preview
fun PreviewPriceChange() {
    BusbyCryptoTrackerTheme {
        PriceChange(
            percentChange = DisplayableNumber(
                value = 2.43,
                formatted = "2.43"
            )
        )
    }
}