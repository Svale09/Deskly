package com.example.deskly.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deskly.ui.theme.jomhuriaFontFamily
import com.example.deskly.ui.theme.primaryBlue

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 0.dp),
            fontFamily = jomhuriaFontFamily,
            fontSize = 30.sp
        )
    }
}

@Preview
@Composable
private fun PreviewCustomButton() {
    CustomButton(text = "Log in", onClick = { /*TODO*/ })
}