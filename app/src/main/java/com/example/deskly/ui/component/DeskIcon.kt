package com.example.deskly.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deskly.R
import com.example.deskly.ui.theme.primaryBlue
import com.example.deskly.ui.theme.primaryRed

@Composable
fun DeskItem(
    isReserved: Boolean
) {
    var backgroundColor by remember { mutableStateOf(if (isReserved) primaryRed.copy(alpha = 0.8f) else Color.Transparent) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .let {
                if (isReserved) {
                    it
                } else {
                    it.clickable {
                        backgroundColor =
                            if (backgroundColor == primaryBlue.copy(alpha = 0.5f)) Color.Transparent else primaryBlue.copy(
                                alpha = 0.5f
                            )
                    }
                }
            }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.desk),
            contentDescription = null,
            //tint = if (isReserved) Color.Gray else MaterialTheme.colorScheme.primary, // Change icon color if needed
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
        )
    }
}

@Preview
@Composable
fun DeskIconPreview() {
    DeskItem(isReserved = false)
}