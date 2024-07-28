package cn.bookkeeper.feature.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author kita
 * @since 2024/07/28 19:52
 * email: liaojianzengzhi@gmail.com
 */

internal data class HomeNavigationBarTypeAddBill(
    override val iconImageVector: ImageVector = Icons.Default.Add,
    override val labelResId: Int = R.string.add_bill,
) : HomeNavigationBarType
