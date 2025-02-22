package com.example.obartest.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.address.util.DEFAULT_APP_LOCALE
import com.example.address.util.LocaleManager
import com.example.obartest.ui.navgraph.RootNavGraph
import com.example.obartest.ui.theme.ObarTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObarTestTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        RootNavGraph(navController = navController)
                    }
                }
            }
        }
    }
    override fun attachBaseContext(newBase: Context?) {
        val updatedContext = LocaleManager.setLocale(newBase, DEFAULT_APP_LOCALE)
        super.attachBaseContext(updatedContext)
    }
}