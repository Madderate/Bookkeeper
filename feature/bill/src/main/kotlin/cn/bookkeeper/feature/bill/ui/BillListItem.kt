package cn.bookkeeper.feature.bill.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.bookkeeper.feature.bill.R
import cn.bookkeeper.feature.bill.data.entity.Bill
import cn.bookkeeper.feature.bill.data.entity.BillType
import cn.bookkeeper.feature.bill.data.entity.Company
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestState

private val ColorIncome = Color(0xFFDD2222)
private val ColorExpenses = Color(0xFF22AA22)

/**
 * @author kita
 * @since 2024/11/16 17:39
 * email: liaojianzengzhi@gmail.com
 */
@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun BillListItem(
    modifier: Modifier = Modifier,
    bill: Bill,
    onClick: () -> Unit,
) {
    Card(modifier = modifier.fillMaxWidth(), onClick = onClick) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val billType = bill.type
            GlideSubcomposition(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = billType.iconModel,
            ) {
                when (state) {
                    is RequestState.Success -> {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painter,
                            contentDescription = billType.name
                        )
                    }

                    RequestState.Failure, RequestState.Loading -> {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Filled.Build,
                            contentDescription = billType.name
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                text = bill.description ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            val billAmount = bill.amount
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = if (billAmount.contains("-".toRegex())) ColorExpenses else ColorIncome,
                text = billAmount
            )
            val transactionMethod = bill.transactionMethod
            Image(
                modifier = Modifier.size(32.dp),
                imageVector = when (transactionMethod) {
                    Bill.TransactionMethod.ALIPAY -> Icons.Filled.PlayArrow
                    Bill.TransactionMethod.CREDIT_CARD -> Icons.Filled.Create
                    Bill.TransactionMethod.WECHAT -> Icons.Filled.Phone
                    else -> Icons.Filled.Clear
                },
                contentDescription = stringResource(
                    when (transactionMethod) {
                        Bill.TransactionMethod.ALIPAY -> R.string.bill_pay_type_alipay
                        Bill.TransactionMethod.CREDIT_CARD -> R.string.bill_pay_type_credit_card
                        Bill.TransactionMethod.WECHAT -> R.string.bill_pay_type_wechat
                        else -> R.string.bill_pay_type_unknown
                    }
                )
            )
        }
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 16.dp),
            text = bill.formattedDateTime,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Preview
@Composable
private fun BillItem() {
    val bill = Bill(
        id = "239857192348",
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
    )
    MaterialTheme {
        BillListItem(bill = bill) {}
    }
}
