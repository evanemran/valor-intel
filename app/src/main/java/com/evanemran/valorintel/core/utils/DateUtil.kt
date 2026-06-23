package com.evanemran.valorintel.core.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateUtil {
    private val isoParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    private val humanFormat = SimpleDateFormat("MMM d, yyyy – h:mm a", Locale.US)

    fun formatToHumanReadable(isoDateString: String?): String {
        if (isoDateString.isNullOrBlank()) return "Invalid date"
        return try {
            // Trim fractional seconds / trailing "Z" so the parser stays lenient.
            val cleaned = isoDateString.substringBefore('.').removeSuffix("Z")
            val date = isoParser.parse(cleaned) ?: return "Invalid date"
            humanFormat.format(date)
        } catch (e: Exception) {
            "Invalid date"
        }
    }
}