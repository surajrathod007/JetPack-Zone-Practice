package com.surajrathod.jetpackzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.surajrathod.jetpackzone.ui.theme.JetPackZoneTheme

data class Header(
    val title: String,
    val items: List<String>
) {
    companion object {
        fun getHeaders(): List<Header> {
            var list: MutableList<Header> = emptyList<Header>().toMutableList()
            for (i in 1..100) {
                val items: MutableList<String> = emptyList<String>().toMutableList()
                for (j in 1..6) {
                    items.add(j.toString())
                }
                list.add(
                    Header(
                        title = i.toString(),
                        items = items
                    )
                )
            }
            return list
        }

        fun getColorsList(): List<Color> {
            val list = listOf<Color>(
                Color.Cyan,
                Color.Green,
                Color.Black,
                Color.Magenta,
                Color.Red,
                Color.Yellow
            )
            return list
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackZoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItem = remember {
                        mutableStateOf("")
                    }
                    Column() {
                        Text(
                            text = selectedItem.value,
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        MyListView() {
                            selectedItem.value = it
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyListView(onItemClick: (String) -> Unit) {
    LazyColumn {
        items(Header.getHeaders()) {
            HeaderItems(headers = it) {
                onItemClick(it)
            }
        }
    }
}

@Composable
fun HeaderItems(headers: Header, onItemClick: (String) -> Unit) {
    Column() {
        Text(text = headers.title)
        LazyRow() {
            items(headers.items) {
                Item(it, Header.getColorsList()[headers.items.indexOf(it)]) {
                    onItemClick("Value of i = ${headers.title} j = $it")
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun Item(
    name: String = "Sample Text",
    color: Color = Color.Black,
    onItemClick: (String) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(color)
            .clickable {
                onItemClick(name)
            }
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .align(Alignment.Center)
                .background(Color.Cyan.copy(alpha = 0.5f))
        ) {
            Text(text = name, modifier = Modifier.align(Alignment.Center))
        }
    }

}


@Composable
fun ListComposable(myList: List<String> = listOf("Suraj", "King")) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("$item")
            }
        }
        Text("  Count: ${myList.count()}")
    }
}


@Composable
fun GreetingPreview() {
    JetPackZoneTheme {

    }
}