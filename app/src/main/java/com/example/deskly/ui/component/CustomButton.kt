package com.example.deskly.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.deskly.ui.theme.primaryBlue
import com.google.firebase.auth.FirebaseUser

@Composable
fun CustomButton(
    text: String,
    onClick: LiveData<FirebaseUser?>,
    modifier: Modifier = Modifier
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        ) {
        Text(
            text = text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewCustomButton() {
    CustomButton(text = "Log in", onClick = { /*TODO*/ })
}