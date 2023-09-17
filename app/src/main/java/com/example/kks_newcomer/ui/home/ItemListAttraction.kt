package com.example.kks_newcomer.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.kks_newcomer.data.Attraction

@Composable
fun ItemListAttraction(input: Attraction, onClick: ((Attraction) -> Unit)?) {
    MaterialTheme {
        ConstraintLayout(
            modifier = Modifier
                .background(
                    MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
                .clickable {
                    if (onClick != null) onClick(input)
                }
                .fillMaxWidth()
        ) {
            val (image, title) = createRefs()
            AttractionImage(url = input.images?.firstOrNull()?.src,
                modifier = Modifier
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.value(128.dp)
                        height = Dimension.value(72.dp)
                    }
            )
            AttractionTitle(title = input.name,
                modifier = Modifier
                    .constrainAs(title) {
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                        start.linkTo(image.end)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

@Composable
fun AttractionImage(
    url: String?, modifier: Modifier = Modifier
) {
    AsyncImage(
        model = url, contentDescription = null, modifier = modifier
    )
}

@Composable
fun AttractionTitle(
    title: String?, modifier: Modifier = Modifier
) {
    Text(
        text = title ?: "",
        style = MaterialTheme.typography.h6,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewAttractionIcon() {
    AttractionImage(url = "https://www.travel.taipei/image/373672")
}

@Preview
@Composable
fun PreviewAttractionTitle() {
    AttractionTitle(title = "台灣省政治建設協會辦公處(舊址)")
}

@Preview
@Composable
fun PreviewListItemAttraction() {
    ItemListAttraction(
        input = Attraction(
            id = 0L,
            name = "台灣省政治建設協會辦公處(舊址)",
            introduction = null,
            address = null,
            tel = null,
            latitude = null,
            longitude = null,
            officialSite = null,
            facebook = null,
            remind = null,
            url = null,
            images = listOf(
                com.example.kks_newcomer.data.Image(
                    src = "https://www.travel.taipei/image/373672", subject = "", ext = ".jpg"
                )
            )
        ),
        onClick = null
    )
}