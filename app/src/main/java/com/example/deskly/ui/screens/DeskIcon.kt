import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DeskIcon(
    isReserved: Boolean
) {
    var backgroundColor by remember { mutableStateOf(if (isReserved) Color.Red else Color.Transparent) }

    Box(
        modifier = Modifier
            .height(48.dp)
            .width(48.dp)
            .background(backgroundColor)
            .let {
                if (isReserved) {
                    it
                } else {
                    it.clickable {
                        backgroundColor = Color.Blue
                    }
                }
            }
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            //tint = if (isReserved) Color.Gray else MaterialTheme.colorScheme.primary, // Change icon color if needed
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
        )
    }
}

@Preview
@Composable
fun DeskIconPreview() {
    DeskIcon(isReserved = true)
}