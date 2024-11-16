package cn.bookkeeper.feature.bill.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.bookkeeper.feature.bill.data.entity.Bill
import cn.bookkeeper.feature.bill.data.entity.BillType
import cn.bookkeeper.feature.bill.data.entity.Company
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 * @author kita
 * @since 2024/11/16 17:43
 * email: liaojianzengzhi@gmail.com
 */
@Composable
fun BillList(
    modifier: Modifier = Modifier,
    bills: ImmutableList<Bill>,
    onItemClick: (position: Int) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(bills, key = { _, item -> item.id }) { index, content ->
            BillListItem(
                modifier = Modifier.padding(
                    top = if (index == 0) 8.dp else 4.dp,
                    bottom = if (index == bills.size - 1) 8.dp else 4.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
                bill = content
            ) {
                onItemClick(index)
            }
        }
    }
}

@Preview
@Composable
private fun BillListPreview() {
    val items = persistentListOf(
        Bill(
            id = "123598734",
            type = BillType(
                id = "eac234",
                name = "餐饮",
            ),
            description = "喝可乐！",
            amount = "-2.5",
            timestamp = 1731771705201L,
            company = Company(
                id = "1259879",
                name = "全家",
                location = "上海市杨浦区逸仙路某个号"
            ),
            transactionMethod = Bill.TransactionMethod.ALIPAY
        ),
        Bill(
            id = "87908457912394",
            type = BillType(
                id = "eac234",
                name = "送钱",
            ),
            description = "送钱",
            amount = "22.5",
            timestamp = 1731771705201L,
            company = Company(
                id = "1259879",
                name = "全家",
                location = "上海市杨浦区逸仙路某个号"
            ),
            transactionMethod = Bill.TransactionMethod.ALIPAY
        )
    )
    MaterialTheme {
        BillList(modifier = Modifier.fillMaxHeight(), bills = items) {

        }
    }
}
