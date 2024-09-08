package otus.gbp.viewcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import otus.gbp.viewcompose.R

@Composable
fun LikeDislikeCompose(likes: Int, modifier: Modifier = Modifier, onDislike: () -> Unit, onLike: () -> Unit) {
    Row(
        modifier = modifier
            .defaultMinSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.light_grey),
                        colorResource(id = R.color.white)
                    )
                ),
                shape = RoundedCornerShape(4.dp)
            )
            .border(2.dp, colorResource(id = R.color.black), RoundedCornerShape(4.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(modifier = Modifier.padding(horizontal = 24.dp), onClick = onDislike) {
            Icon(
                painter = painterResource(id = R.drawable.thumb_down_24),
                tint = colorResource(id = R.color.red),
                contentDescription = stringResource(id = R.string.dislike)
            )
        }
        Text(
            text = likes.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(modifier = Modifier.padding(horizontal = 24.dp), onClick = onLike) {
            Icon(
                painter = painterResource(id = R.drawable.thumb_up_24),
                tint = colorResource(id = R.color.green),
                contentDescription = stringResource(id = R.string.like)
            )
        }
    }
}

@Preview
@Composable
fun LikeDislikeComposePreview() {
    LikeDislikeCompose(10, onDislike = { }, onLike = { })
}