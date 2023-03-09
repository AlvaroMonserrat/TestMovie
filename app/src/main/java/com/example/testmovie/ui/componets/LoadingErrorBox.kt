package com.example.testmovie.ui.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmovie.R
import com.example.testmovie.source.api.ApiResponseState
import com.example.testmovie.ui.theme.TestMovieTheme

@Composable
fun LoadingErrorBox(
    state: ApiResponseState<*>?,
    onResetStatus: () -> Unit
) {

    if (state is ApiResponseState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .padding(8.dp)
                .pointerInput(Unit) {},
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator(
                modifier = Modifier
                    .semantics { testTag = "loading-wheel" }
                    .size(48.dp),
                strokeWidth = 4.dp
            )
        }
    }
    if (state is ApiResponseState.Error) {
        ErrorDialog(
            title = stringResource(id = R.string.error_title),
            text = state.statusMessage.ifEmpty { stringResource(state.messageId) },
            buttonText = stringResource(id = R.string.accept),
            onDismiss = onResetStatus
        )
    }
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun LoadingErrorBoxPreview(){
    TestMovieTheme() {
        LoadingErrorBox(
            state = ApiResponseState.Loading<Any>(),
            onResetStatus = {}
        )
    }
}