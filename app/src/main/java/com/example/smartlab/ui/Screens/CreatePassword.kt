import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.code.PreferencesManager
import com.example.smartlab.code.sha256
import com.example.smartlab.ui.Components.CustomButton


@Composable
fun CreatePassword(navController: NavHostController, context: Context) {
    val preferencesManager = remember { PreferencesManager(context) }
    val passwd = remember { mutableStateOf(preferencesManager.getData("isCompleted", false.toString())) }

    var password by remember { mutableStateOf("") }
    val maxPasswordLength = 4

    if (passwd != null){
        navController.navigate("createCard")
        return
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { navController.navigate("createCard") }) {
                Text("Пропустить", color = Color(0xFF1A6FEE))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Создайте пароль",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily= FontFamily(Font(R.font.nunito_black)),
                lineHeight = 28.sp,
                letterSpacing = 0.033.sp,
                color = Color.Black),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Для защиты ваших персональных данных",
            style = TextStyle(fontSize = 15.sp, color = Color(0xFF939396)),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(56.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 0 until maxPasswordLength) {
                val isFilled = i < password.length
                val circleColor = if (isFilled) Color(0xFF1A6FEE) else Color.White
                Spacer(modifier = Modifier.width(12.dp))
                Surface(
                    shape = CircleShape,
                    color = circleColor,
                    modifier = Modifier.size(16.dp),
                    border = BorderStroke(1.dp, Color(0xFF1A6FEE))
                ){}
                Spacer(modifier = Modifier.width(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(60.dp))


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
            for (i in 0 until 3) {
                Row(
                    modifier = Modifier.width(288.dp).fillMaxWidth().padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (j in 0 until 3) {
                        val number = numbers[i * 3 + j]
                        CustomButton(
                            number = number,
                            onNumberClick = {
                                if(password.length < maxPasswordLength) {
                                    password += it.toString()
                                }
                            }
                        )
                    }
                }
            }

            if (password.length == maxPasswordLength){
                preferencesManager.saveData("guardPassword", sha256(password))
                passwd.value = password

                navController.navigate("createCard")
            }

            Row(
                modifier = Modifier.width(288.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    number = 0,
                    onNumberClick = {
                        if(password.length < maxPasswordLength) {
                            password += it.toString()
                        }
                    }
                )

                Spacer(modifier = Modifier.width(46.dp))

                Icon(
                    bitmap = ImageBitmap.imageResource(R.drawable.del_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end=23.dp)
                        .height(24.dp)
                        .width(35.dp)
                        .clickable {
                            if (password.isNotEmpty()){
                                password = password.dropLast(1)
                            }
                        },
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreatePasswordPRev() {
    CreatePassword(navController = rememberNavController(), context = LocalContext.current)
}