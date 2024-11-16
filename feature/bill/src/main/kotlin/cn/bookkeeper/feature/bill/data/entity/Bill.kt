package cn.bookkeeper.feature.bill.data.entity

import android.icu.util.Calendar
import androidx.annotation.IntDef
import java.util.Locale

private const val FORMAT_DATE_TIME = "%d.%02d.%02d %02d:%02d:%02d"

/**
 * @param amount 金额，可以是正数（收入），也可以是负数（支出）
 * @param transactionMethod 交易方式
 *
 * @author kita
 * @since 2024/11/16 17:44
 * email: liaojianzengzhi@gmail.com
 */
data class Bill(
    val id: String,
    val type: BillType,
    val description: String?,
    val amount: String,
    val timestamp: Long,
    val company: Company,
    @TransactionMethod val transactionMethod: Int,
) {
    val year: Int
        get() = Calendar.getInstance(Locale.CHINA).apply { timeInMillis = timestamp }
            .get(Calendar.YEAR)

    val month: Int
        get() = Calendar.getInstance(Locale.CHINA).apply { timeInMillis = timestamp }
            .get(Calendar.MONTH) + 1

    val dayOfMonth: Int
        get() = Calendar.getInstance(Locale.CHINA).apply { timeInMillis = timestamp }
            .get(Calendar.DAY_OF_MONTH)

    val hour: Int
        get() = Calendar.getInstance(Locale.CHINA).apply { timeInMillis = timestamp }
            .get(Calendar.HOUR_OF_DAY)

    val minute: Int
        get() = Calendar.getInstance(Locale.CHINA).apply { timeInMillis = timestamp }
            .get(Calendar.MINUTE)

    val second: Int
        get() = Calendar.getInstance(Locale.CHINA).apply { timeInMillis = timestamp }
            .get(Calendar.SECOND)

    var formattedDateTime: String = ""
        private set
        get() {
            if (field.isBlank()) {
                field = FORMAT_DATE_TIME.format(year, month, dayOfMonth, hour, minute, second)
            }
            return field
        }

    @IntDef(
        TransactionMethod.UNKNOWN,
        TransactionMethod.ALIPAY,
        TransactionMethod.WECHAT,
        TransactionMethod.CREDIT_CARD
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class TransactionMethod {
        companion object {
            const val UNKNOWN = 0
            const val ALIPAY = 1
            const val WECHAT = 2
            const val CREDIT_CARD = 3
        }
    }
}
