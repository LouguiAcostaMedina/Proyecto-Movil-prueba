package pe.edu.pe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.upeu.pharmamobil.App
import pe.edu.upeu.pharmamobil.presentation.producto.ProductoScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            ProductoScreen()
        }
    }
}

