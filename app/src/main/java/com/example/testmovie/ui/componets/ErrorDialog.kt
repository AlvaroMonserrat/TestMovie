package com.example.testmovie.ui.componets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmovie.ui.theme.TestMovieTheme

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    buttonText: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = modifier.semantics { testTag = "error-dialog" },
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        text = { Text(text = text, fontSize = 14.sp) },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                modifier = modifier.padding(8.dp)
            ) {
                Text(
                    text = buttonText,
                    style = TextStyle(
                        fontSize = 16.sp,
                    ),
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
fun ErrorDialogPreview(
    title: String = "Título",
    text: String = "Tristique sollicitudin nibh sit amet commodo nulla facilisi nullam vehicula.",
    buttonText: String = "Aceptar"
) {
    TestMovieTheme() {
        ErrorDialog(title = title, text = text, buttonText = buttonText) {
        }
    }
}