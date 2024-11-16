package cn.bookkeeper.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import cn.bookkeeper.feature.bill.data.entity.Bill
import cn.bookkeeper.feature.bill.ui.BillList
import cn.bookkeeper.feature.home.R
import kotlinx.collections.immutable.ImmutableList

/**
 * @author kita
 * @since 2024/11/16 23:56
 * email: liaojianzengzhi@gmail.com
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CheckBills(modifier: Modifier = Modifier, bills: ImmutableList<Bill>) {
    if (bills.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "没有数据呦～", style = MaterialTheme.typography.titleLarge)
        }
        return
    }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Column(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(R.string.check_bills))
            },
            scrollBehavior = scrollBehavior
        )
        BillList(bills = bills) {

        }
    }
}
