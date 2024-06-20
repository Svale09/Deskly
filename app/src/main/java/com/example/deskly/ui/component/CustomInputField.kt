package com.example.deskly.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    isPasswordField: Boolean = false,
    modifier: Modifier = Modifier,
    errorState: Boolean
) {
    //val state = remember { mutableStateOf(value) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = label,
            fontFamily = jomhuriaFontFamily,
            fontSize = 30.sp,
            modifier = Modifier
        )
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            isError = errorState,
            visualTransformation = if (isPasswordField && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                errorContainerColor = Color.Red.copy(alpha = 0.3f),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedPlaceholderColor = Color.LightGray,
                focusedPlaceholderColor = Color.LightGray,
            ),
            placeholder = { Text(text = placeholder) },
            trailingIcon = {
                if (isPasswordField) {
                    val image =
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            },
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
        onValueChange = { newInput -> input = newInput },
        errorState = false
    )
}
