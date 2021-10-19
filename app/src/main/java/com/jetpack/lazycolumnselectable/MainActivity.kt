package com.jetpack.lazycolumnselectable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.lazycolumnselectable.ui.theme.LazyColumnSelectableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnSelectableTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ItemSelectable()
                }
            }
        }
    }
}

@Composable
fun ItemSelectable() {
    val selectedIndex = remember { mutableStateOf(-1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Item Selectable",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(count = 10) {index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp, 10.dp, 5.dp)
                        .wrapContentHeight(
                            align = CenterVertically
                        )
                        .selectable(
                            selected = selectedIndex.value == index,
                            onClick = {
                                selectedIndex.value = if (selectedIndex.value != index)
                                    index else -1
                            }
                        ),
                    elevation = 5.dp,
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = if (selectedIndex.value == index)
                        Color(0xFFFF0000) else Color(0xFFFFFFFF)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cat),
                                contentDescription = "Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.padding(5.dp))

                            Column {
                                Text(
                                    text = "Sample Name",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.padding(2.dp))

                                Text(
                                    text = "Lorem Ipsum is simply dummy text of the printing.",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



























