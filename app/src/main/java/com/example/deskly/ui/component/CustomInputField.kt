package com.example.deskly.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deskly.ui.theme.jomhuriaFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = remember { mutableStateOf(value) }

    Column(modifier = modifier) {
        Text(
            text = label,
            fontFamily = jomhuriaFontFamily,
            fontSize = 30.sp,
            //style = MaterialTheme.typography.caption, Todo add custom style
            modifier = Modifier
        )
        TextField(
            value = state.value,
            onValueChange = {
                state.value = it
                onValueChange(it)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray
            ),
            placeholder = { Text(text = placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp)
                .shadow(shape = RoundedCornerShape(16.dp), elevation = 3.dp)
                .border(width = 2.dp, color = Color(0xFFE0E0E0))
        )
    }
}

@Preview
@Composable
private fun CustomInputFieldPreview() {
    var input: String
    input = ""
    CustomInputField(
        label = "Email",
        placeholder = "Input your email",
        value = input,
        onValueChange = { newInput -> input = newInput }
    )
}