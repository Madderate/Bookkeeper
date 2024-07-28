package cn.bookkeeper.common.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * @author kita
 * @since 2024/07/28 16:09
 * email: liaojianzengzhi@gmail.com
 */
internal val LightColorScheme = lightColorScheme(
    primary = ColorF1C40F,
    secondary = ColorF39C12,
    background = ColorEFEBE9

)

internal val DarkColorScheme = darkColorScheme(
    primary = ColorE74C3C,
    secondary = ColorC0392B,
    background = Color4E342E
)

@Composable
fun ThemeCommon(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}
