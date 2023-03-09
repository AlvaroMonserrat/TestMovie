package com.example.testmovie.ui.componets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmovie.R
import com.example.testmovie.model.PopularMovie
import com.example.testmovie.model.popularMovieDummy
import com.example.testmovie.ui.theme.MidnightBlue
import com.example.testmovie.ui.theme.TestMovieTheme


@Composable
fun CardMovie(popularMovie: PopularMovie){

    val expanded = rememberSaveable { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .semantics { testTag = "card-popular-movie" }
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ){

        Column(modifier= Modifier
            .weight(1f, true)
            .padding(12.dp)) {
                Text(
                    text = popularMovie.originalTitle,
                    color = MidnightBlue,
                )

                if(expanded.value)
                {
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        text=popularMovie.overview,
                        textAlign = TextAlign.Justify,
                        color = MidnightBlue,
                        fontWeight = FontWeight.Light
                    )
                }
            }

            IconButton(
                modifier = Modifier.semantics { testTag = "icon-movie-description" },
                onClick = { expanded.value = !expanded.value },
            ) {
                Icon(
                    imageVector = if(expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    tint = MidnightBlue,
                    contentDescription = if (expanded.value) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )

            }
        }
    }
}

@Composable
@Preview
fun CardMoviePreview(){
    TestMovieTheme() {
        CardMovie(popularMovie = popularMovieDummy())
    }
}