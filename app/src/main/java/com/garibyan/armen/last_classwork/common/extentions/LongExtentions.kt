package com.garibyan.armen.last_classwork.common.extentions

import java.text.SimpleDateFormat

fun Long.convertToDate(): String = SimpleDateFormat("hh:mm a").format(this)