package Componentes
import Preguntas.Preguntas
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.practica2aplicacionpreguntas.R
import kotlin.random.Random
@Composable
fun Juego(){

    var lista = ArrayList<Preguntas>()


    //Creamos las diferentes preguntas,con las imagenes y respuestas
    lista.add(Preguntas("¿Es pikachu el más fuerte?", R.drawable.pickachu, false))
    lista.add(Preguntas("¿Es más rapido Raycuaza que un Miltank?", R.drawable.raycuaza ,false))
    lista.add(Preguntas("¿Mewtwo puede aprender bola sombra?", R.drawable.mewtwo, true))
    lista.add(Preguntas("¿Melmetal pesa mas que Snorlax?", R.drawable.melmeltal, true))
    lista.add(Preguntas("¿Mide mas Blisin que Snorlax?", R.drawable.blisin, false))

    var numeroRandom by remember { mutableStateOf(Random.nextInt(3)) }
    var retroalimentacionTexto by remember { mutableStateOf("") }
    var retroalimentacionColor by remember { mutableStateOf(Color.Transparent) }
    var respuestaUsuario by remember { mutableStateOf(false) }
    var indicePregunta by remember { mutableStateOf(0) }

    // Variable para las preguntas aleatorias
    var preguntaAleatoria by remember { mutableStateOf(Random.nextInt(3)) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(

            text = lista[preguntaAleatoria].pregunta,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )
        // Imagen centrada y de mayor tamaño
        Image(
            painter = painterResource(id = lista[preguntaAleatoria].imagenes),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(16.dp)
        )
        // Espacio entre la imagen y los botones
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = retroalimentacionTexto,
            color = retroalimentacionColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp),
            fontSize = 20.sp
        )

        Column(verticalArrangement = Arrangement.Bottom) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (lista[preguntaAleatoria].respuestas) {
                            retroalimentacionTexto = "Muy bien entrenador"
                            retroalimentacionColor = Color.Green
                        } else {
                            retroalimentacionTexto = "Te queda mucho entrenador"
                            retroalimentacionColor = Color.Red
                        }
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("Si")
                }
                Button(
                    onClick = {
                        if (!lista[preguntaAleatoria].respuestas) {
                            retroalimentacionTexto = "Muy bien entrenador"
                            retroalimentacionColor = Color.Green
                        } else {
                            retroalimentacionTexto = "Te queda mucho entrenador"
                            retroalimentacionColor = Color.Red
                        }
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("No")
                }
            }
            // Botones de PREV y NEXT
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        retroalimentacionTexto = ""
                        retroalimentacionColor = Color.Transparent

                        if (preguntaAleatoria > 0) {
                            preguntaAleatoria--
                        } else {
                            preguntaAleatoria = lista.size - 1
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                        Text(text = "PREV")
                    }
                }
                Button(
                    onClick = {
                        retroalimentacionTexto = ""
                        retroalimentacionColor = Color.Transparent

                        if (preguntaAleatoria < lista.size - 1) {
                            preguntaAleatoria++
                        } else {
                            preguntaAleatoria = 0
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "")
                        Text(text = "NEXT")
                    }
                }
                // Botón "RANDOM" para mostrar una pregunta al azar
                Button(
                    onClick = {
                        //Indice aleatorio diferente al actual
                        preguntaAleatoria = Random.nextInt(3)
                    },
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Text("RANDOM")
                }
            }
        }
    }
}
//Funcion para ejecutar la aplicacion
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewJuego(){
    Juego()
}
//Funcion para verificar el color si has acertado la respuesta o no
fun verificarColor(acierto: Boolean): Color {
    if (acierto) {
        return Color.Green
    } else {
        return Color.Red
    }
}
//Esta funcion la busque con el ChatGPT ya que estuve trabado por un tiempo y tenia que avanzar
@Composable
fun BotonRespuesta(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp),
    ) {
        Text(text = texto)
    }
}
//Funcion para verificar la respuesta
fun verificarRespuesta(pregunta: Preguntas, respuestaUsuario: Boolean) {
    val esCorrecta = pregunta.respuestas == respuestaUsuario
    // Debes manejar las variables de retroalimentación en esta función o pasarlas como argumentos.
}
//Funcion para avanzar a la siguiente pregunta
fun avanzarAPreguntaSiguiente(numeroRandom: Int, lista: List<Preguntas>): Int {
    if (numeroRandom < lista.size - 1) {
        return numeroRandom + 1
    } else {
        return numeroRandom
    }
}

