package cn.bookkeeper.feature.bill.data.entity

import androidx.annotation.DrawableRes

/**
 * @author kita
 * @since 2024/11/16 18:30
 * email: liaojianzengzhi@gmail.com
 */
data class BillType(
    val id: String,
    val name: String,
    @DrawableRes val iconResId: Int = 0,
    val iconUrl: String? = null,
) {
    val iconModel: Any
        get() = iconUrl?.takeIf { it.isNotBlank() } ?: iconResId
}
