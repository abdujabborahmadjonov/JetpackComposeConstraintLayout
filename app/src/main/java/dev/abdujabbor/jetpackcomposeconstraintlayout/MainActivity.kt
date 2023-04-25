package dev.abdujabbor.jetpackcomposeconstraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val constraint = ConstraintSet {
                val greenbox = createRefFor("greenbox")
                val redbox = createRefFor("redbox")
                val guidline =createGuidelineFromTop(0.5f)

                constrain(greenbox) {
                    top.linkTo(guidline)
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                constrain(redbox) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(greenbox.end)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                createHorizontalChain(greenbox,redbox, chainStyle = ChainStyle.Packed)

            }
            ConstraintLayout(
                constraint, modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
                    .layoutId("greenbox"))
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
                    .layoutId("redbox"))
            }
        }
    }
}