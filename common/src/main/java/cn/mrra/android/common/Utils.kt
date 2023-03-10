package cn.mrra.android.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import cn.mrra.android.common.base.appContext

private var toast: Toast? = null

fun toastMsg(
    msg: String,
    context: Context = appContext,
    time: Int = Toast.LENGTH_LONG
) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        toast?.cancel()
        toast = Toast.makeText(context, msg, time).apply { show() }
    } else {
        Handler(Looper.getMainLooper()).post {
            toast?.cancel()
            toast = Toast.makeText(context, msg, time).apply { show() }
        }
    }
}

inline fun <reified A : Activity> Context.startActivity(builder: Intent.() -> Unit = {}) {
    startActivity(Intent(this, A::class.java).apply(builder))
}

val Context.isDarkMode: Boolean
    get() = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK ==
            Configuration.UI_MODE_NIGHT_YES

val Configuration.isDarkMode: Boolean
    get() = uiMode and Configuration.UI_MODE_NIGHT_MASK ==
            Configuration.UI_MODE_NIGHT_YES

val Context.statusBarHeight: Int
    get() {
        var result = 0
        val resourceId = resources.getIdentifier(
            "status_bar_height",
            "dimen",
            "android"
        )
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }