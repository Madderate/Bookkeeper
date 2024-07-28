package cn.bookkeeper.feature.home

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cn.bookkeeper.common.ui.ThemeCommon

/**
 * @author kita
 * @since 2024/07/28 18:13
 * email: liaojianzengzhi@gmail.com
 */

private const val TAG = "Home"

@Composable
fun Home() {
    ThemeCommon {
        val allTypes = arrayOf(
            HomeNavigationBarTypeCheckBills(),
            HomeNavigationBarTypeAddBill(),
            HomeNavigationBarTypeMine()
        )
        var selectedNavigationType: HomeNavigationBarType by remember {
            mutableStateOf(allTypes.first())
        }
        Scaffold(
            bottomBar = {
                HomeNavigationBar(
                    types = allTypes,
                    selectedType = selectedNavigationType,
                    onItemClick = {
                        selectedNavigationType = it
                    }
                )
            }
        ) {
            CheckBills(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
internal fun HomeNavigationBar(
    modifier: Modifier = Modifier,
    types: Array<HomeNavigationBarType>,
    selectedType: HomeNavigationBarType?,
    onItemClick: (type: HomeNavigationBarType) -> Unit,
) {
    NavigationBar(modifier = modifier) {
        types.forEach {
            NavigationBarItem(
                selected = selectedType == it,
                onClick = { onItemClick(it) },
                icon = {
                    Icon(
                        imageVector = it.iconImageVector,
                        contentDescription = stringResource(it.labelResId)
                    )
                },
                label = {
                    Text(text = stringResource(it.labelResId))
                }
            )
        }
    }
}

interface HomeNavigationBarType {
    val iconImageVector: ImageVector

    @get:StringRes
    val labelResId: Int
}

@Preview(name = "light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeTopBarPreview() {
    val allTypes = arrayOf(
        HomeNavigationBarTypeCheckBills(),
        HomeNavigationBarTypeAddBill(),
        HomeNavigationBarTypeMine()
    )
    ThemeCommon {
        var selectedType: HomeNavigationBarType by remember {
            mutableStateOf(allTypes.first())
        }
        HomeNavigationBar(
            types = allTypes,
            selectedType = selectedType,
            onItemClick = {
                selectedType = it
            }
        )
    }
}
