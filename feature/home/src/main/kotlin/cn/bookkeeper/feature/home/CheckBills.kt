package cn.bookkeeper.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.bookkeeper.common.ui.ThemeCommon

/**
 * @author kita
 * @since 2024/07/28 18:54
 * email: liaojianzengzhi@gmail.com
 */

private val sItems = (0..<26).toList().map { ('a' + it).toString() }

@Composable
internal fun CheckBills(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(sItems, key = { _, item -> item }) { index, content ->
            BillItem(
                modifier = Modifier.padding(
                    top = if (index == 0) 8.dp else 4.dp,
                    bottom = if (index == sItems.size - 1) 8.dp else 4.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
                content = content
            )
        }
    }
}

@Composable
private fun BillItem(modifier: Modifier = Modifier, content: String) {
    OutlinedCard(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium,
            text = content
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = "${System.currentTimeMillis()}"
        )
    }
}

internal data class HomeNavigationBarTypeCheckBills(
    override val iconImageVector: ImageVector = Icons.Default.DateRange,
    override val labelResId: Int = R.string.check_bills,
) : HomeNavigationBarType

@Preview(name = "light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BillItemPreview() {
    ThemeCommon {
        Surface {
            BillItem(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxWidth(),
                content = "123"
            )
        }
    }
}

@Preview(name = "light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CheckBillsPreview() {
    ThemeCommon {
        Surface {
            CheckBills()
        }
    }
}
