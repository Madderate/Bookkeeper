package cn.bookkeeper.feature.bill.data.entity

/**
 * @author kita
 * @since 2024/11/16 17:48
 * email: liaojianzengzhi@gmail.com
 */
data class Company(
    val id: String,
    val name: String,
    val location: String? = null,
    val latLng: LatLng? = null,
    val officialWebUrl: String? = null,
) {
    data class LatLng(val latitude: Double, val longitude: Double)
}
