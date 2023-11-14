package com.bright.ecommerce.ui.theme

//import android.app.Activity
//import android.os.Build
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.darkColorScheme
//import androidx.compose.material3.dynamicDarkColorScheme
//import androidx.compose.material3.dynamicLightColorScheme
//import androidx.compose.material3.lightColorScheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.SideEffect
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalView
//import androidx.core.view.WindowCompat
//
//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)
//
//@Composable
//fun EcommerceTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}


import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat

//private val DarkColorPalette = darkColors(
//    primary = PurpleGrey40,
//    primaryVariant = Color.White,
//    secondary = Pink40,
//
//)
//
//private val LightColorPalette = lightColors(
//    primary = Pink40,
//    primaryVariant = Color.White,
//    secondary = Color.LightGray


//private val DarkColorPalette = darkColors(
//    primary = Dark_Background,
//    primaryVariant = Light,
//    secondary = Dark_BackgroudVarient,
//    onPrimary = Dark_OnPrimary
//)
//
//private val LightColorPalette = lightColors(
//    primary = Light_Background,
//    primaryVariant = Black,
//    secondary = Light_BackgroundVarient,
//    onPrimary = Light_OnPrimary



// Assuming these are color values defined elsewhere in your code
val Dark_Background = Black// your color value
val Light = White// your color value
val Dark_BackgroundVariant = Gray// your color value
val Dark_OnPrimary = Black// your color value

val Light_Background = White// your color value
val Light_BackgroundVariant = Gray// your color value
val Light_OnPrimary = White// your color value

    private val DarkColorPalette: Colors = darkColors(
    primary = Dark_Background,
    primaryVariant = Light,
    secondary = Dark_BackgroundVariant,
    onPrimary = Dark_OnPrimary
)

private val LightColorPalette: Colors = lightColors(
    primary = Light_Background,
    primaryVariant = Black,
    secondary = Light_BackgroundVariant,
    onPrimary = Light_OnPrimary
)


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */


@Composable
fun E_commerceTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {


    val view = LocalView.current

    if (!view.isInEditMode)
    {

        SideEffect {


            val window = (view.context as Activity).window

            window.statusBarColor = if (darkTheme) Black.toArgb() else White.toArgb()
            window.navigationBarColor = if (darkTheme) Black.toArgb() else White.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme

        }
    }


    val colors = if (darkTheme) {
        DarkColorPalette


    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}