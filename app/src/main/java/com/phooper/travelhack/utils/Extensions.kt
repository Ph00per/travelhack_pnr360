package com.phooper.travelhack.utils

import dev.dinoparty.MysqlConnector
import dev.dinoparty.PhotoSession

fun <T : Any> T.accessField(fieldName: String): Any? {
    return javaClass.getDeclaredField(fieldName).let { field ->
        field.isAccessible = true
        return@let field.get(this)
    }
}

fun PhotoSession.getActivatedBarcode(): String? {
    (this.accessField("connector") as MysqlConnector).executeQuery(
        StringBuilder("SELECT ").append(", ").append("BARCODE").append(
            " FROM "
        )
            .append("USER_SESSION").append(" WHERE ").append("PHOTO_MODE_FLG").append(" = '")
            .append(1).append("';").toString()
    ).let { resultSet ->
        return if (!resultSet.next()) {
            null
        } else {
            resultSet.getString("BARCODE")
        }
    }
}