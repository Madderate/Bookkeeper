package cn.bookkeeper.feature.home

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cn.bookkeeper.common.ui.ThemeCommon
import cn.bookkeeper.feature.home.ui.CheckBills
import kotlinx.collections.immutable.persistentListOf

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
            HomeNavigationBarType.CheckBills(),
            HomeNavigationBarType.AddBill(),
            HomeNavigationBarType.Mine()
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
            if (selectedNavigationType is HomeNavigationBarType.CheckBills) {
                CheckBills(
                    modifier = Modifier
                        .padding(
                            start = it.calculateStartPadding(layoutDirection = LocalLayoutDirection.current),
                            end = it.calculateEndPadding(layoutDirection = LocalLayoutDirection.current),
                            bottom = it.calculateBottomPadding()
                        )
                        .fillMaxSize(),
                    bills = persistentListOf()
                )
            } else {
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.wip)
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeNavigationBar(
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

internal sealed interface HomeNavigationBarType {
    val iconImageVector: ImageVector

    @get:StringRes
    val labelResId: Int

    data class CheckBills(
        override val iconImageVector: ImageVector = Icons.Default.DateRange,
        override val labelResId: Int = R.string.check_bills,
    ) : HomeNavigationBarType

    data class AddBill(
        override val iconImageVector: ImageVector = Icons.Default.Add,
        override val labelResId: Int = R.string.add_bill,
    ) : HomeNavigationBarType

    data class Mine(
        override val iconImageVector: ImageVector = Icons.Default.Person,
        override val labelResId: Int = R.string.mine,
    ) : HomeNavigationBarType
}

@Preview(name = "light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeNavigationBarPreview() {
    val allTypes = arrayOf(
        HomeNavigationBarType.CheckBills(),
        HomeNavigationBarType.AddBill(),
        HomeNavigationBarType.Mine()
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
