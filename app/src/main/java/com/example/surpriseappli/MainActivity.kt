package com.example.surpriseappli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import coil3.request.crossfade
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.compose.ui.platform.LocalContext
import com.example.surpriseappli.ui.theme.SurpriseAPPliTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = androidx.compose.ui.platform.LocalContext.current
            val imageLoader = remember {
                ImageLoader.Builder(context)
                    .components {
                        if (android.os.Build.VERSION.SDK_INT >= 28) {
                            add(AnimatedImageDecoder.Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }
                    .build()
            }
            SurpriseAPPliTheme {
                SurpriseScreen(imageLoader)
            }
        }
    }
}

@Composable
fun SurpriseScreen(imageLoader: ImageLoader) {
    val backgroundColor = Color(0xFFC62828) // Deep Red
    val context = LocalContext.current

    // Background Audio Player
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }
    
    LaunchedEffect(Unit) {
        val resId = context.resources.getIdentifier("birthday_noise", "raw", context.packageName)
        if (resId != 0) {
            val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/$resId")
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
            exoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_OFF
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.myqueen),
                            contentDescription = "Main Placeholder",
                            modifier = Modifier
                                .size(150.dp, 200.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Happy Birthdayy!!!",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = "Your already 19 wow time flies so fast i swear we were so young like 15 and 16 and kron look at us were growing and were growing together, i hope you enjoy your birthday babyy i lovee youuu soo much" +
                                    "and always take care",
                            color = Color.White,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        // GIF Image
                        AsyncImage(
                            model = ImageRequest.Builder(androidx.compose.ui.platform.LocalContext.current)
                                .data("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExNHJqZ2x6bmJ4Z2x6bmJ4Z2x6bmJ4Z2x6bmJ4Z2x6bmJ4Z2x6JmVwPXYxX2ludGVybmFsX2dpZl9ieV9pZCZjdD1n/LROWHOfTm1Z1S/giphy.gif")
                                .crossfade(true)
                                .build(),
                            imageLoader = imageLoader,
                            contentDescription = "Birthday GIF",
                            modifier = Modifier
                                .size(200.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }



                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.photos),
                            contentDescription = "Photos",
                            modifier = Modifier
                                .size(300.dp, 200.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Wow Almost 4 years as i made this on august 24 2026 soo WOW ALMOST 4 YEARS all those times we had go through ups and downs the u were there in my lowest and you will" +
                                    "always be the one and only in my life I LOVEEE YOUU SOOO MUCHH MY PRETTY BABIIIIII HAPPY BIRTHDAY (made this while ga away ta so yeah thats how much i love you) many birthdays to come i hope kita pagyud in the future ive planned alot for us " +
                                    "i lovee youuu soo soo muchhh babyyyy :D",
                            color = Color.White,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "I LOVE YOUUU",
                            color = Color.White,
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(androidx.compose.ui.platform.LocalContext.current)
                                    .data(R.drawable.makesweet_65klmq)
                                    .crossfade(true)
                                    .build(),
                                imageLoader = imageLoader,
                                contentDescription = "Sweet GIF",
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }
            ConfettiAnimation()
        }
    }
}

data class ConfettiPiece(
    val x: Float,
    val yOffset: Float,
    val color: Color,
    val speed: Float,
    val size: Float,
    val rotationSpeed: Float
)

@Composable
fun ConfettiAnimation() {
    val pieces = remember {
        List(150) {
            ConfettiPiece(
                x = Random.nextFloat(),
                yOffset = Random.nextFloat() * 1000f,
                color = listOf(
                    Color.Yellow, Color.Cyan, Color.Magenta,
                    Color.Green, Color(0xFFFFA500), Color.White
                ).random(),
                speed = 200f + Random.nextFloat() * 300f,
                size = 5f + Random.nextFloat() * 15f,
                rotationSpeed = Random.nextFloat() * 360f
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "confetti")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        pieces.forEach { piece ->
            val yPos = (piece.yOffset + time * height * 2) % (height + 100f) - 50f
            val rotation = (time * piece.rotationSpeed) % 360f
            
            withTransform({
                rotate(rotation, Offset(piece.x * width + piece.size / 2, yPos + piece.size / 2))
            }) {
                drawRect(
                    color = piece.color,
                    topLeft = Offset(piece.x * width, yPos),
                    size = androidx.compose.ui.geometry.Size(piece.size, piece.size)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SurprisePreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val imageLoader = remember { ImageLoader.Builder(context).build() }
    SurpriseAPPliTheme {
        SurpriseScreen(imageLoader)
    }
}
