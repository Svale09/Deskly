package com.example.deskly.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.deskly.R
import com.example.deskly.ui.theme.primaryRed

@Composable
fun IconAndLabelButton(
    title: String,
    vector: ImageVector,
    onClick: () -> Unit,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = vector,
                contentDescription = title,
                tint = color
            )
        }
        Text(text = title)
    }
}

@Composable
@Preview
private fun IconAndLabelButtonPreview() {
    IconAndLabelButton(
        vector = ImageVector.vectorResource(id = R.drawable.add_desk),
        title = "Add desk",
        onClick = {},
        color = primaryRed)
}
