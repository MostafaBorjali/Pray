package com.borjali.mostafa.pray.extention

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.borjali.mostafa.pray.domain.model.NetworkStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.Serializable
import java.net.InetSocketAddress
import java.net.Socket

fun Fragment.shortVibratePhone() {
    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(25)
    }
}

fun Fragment.longVibratePhone() {
    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(500)
    }
}

fun TextView.setTextAnimation(
    text: Spanned,
    duration: Long = 300,
    completion: (() -> Unit)? = null
) {
    fadOutAnimation(duration) {
        this.text = text
        fadInAnimation(duration) {
            completion?.let {
                it()
            }
        }
    }
}

fun Fragment. colorizeArabicDiacritics(
    text: String,
    color: Int
): SpannableStringBuilder {
    val spannable = SpannableStringBuilder(text)
    val diacriticRegex =
        "[\u064B\u064C\u064D\u064E\u064F\u0650\u0651\u0652\u0653\u0654\u0655]".toRegex()

    diacriticRegex.findAll(text).forEach { matchResult ->
        val start = matchResult.range.first
        val end = matchResult.range.last + 1
        spannable.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    return spannable
}

fun View.fadOutAnimation(
    duration: Long = 300,
    visibility: Int = View.INVISIBLE,
    completion: (() -> Unit)? = null
) {
    animate()
        .alpha(0f)
        .setDuration(duration)
        .withEndAction {
            this.visibility = visibility
            completion?.let {
                it()
            }
        }
}

fun View.fadInAnimation(duration: Long = 300, completion: (() -> Unit)? = null) {
    alpha = 0f
    visibility = View.VISIBLE
    animate()
        .alpha(1f)
        .setDuration(duration)
        .withEndAction {
            completion?.let {
                it()
            }
        }
}

fun <T : Serializable?> Bundle.getSerializableCompat(key: String, clazz: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializable(
        key,
        clazz
    )!! else (getSerializable(key) as T)
}

fun Context.isPackageInstalled(packageName: String): Boolean {
    return try {
        packageManager.getLaunchIntentForPackage(packageName)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Fragment.checkInternet(): LiveData<NetworkStatus> {
    val check = MutableLiveData<NetworkStatus>()
    CoroutineScope(Dispatchers.IO).launch {
        executeAsyncTask(
            onPreExecute = {},
            doInBackground = { _: suspend (progress: Int) -> Unit ->
                return@executeAsyncTask try {
                    val sock = Socket()
                    sock.connect(InetSocketAddress("8.8.8.8", 53), 350)
                    sock.close()
                    true
                } catch (e: IOException) {
                    false
                }
            },
            onPostExecute = {
                check.postValue(if (it) NetworkStatus.Available else NetworkStatus.Unavailable)
            },
            onProgressUpdate = {}
        )

    }
    return check
}

fun <P, R> CoroutineScope.executeAsyncTask(
    onPreExecute: () -> Unit,
    doInBackground: suspend (suspend (P) -> Unit) -> R,
    onPostExecute: (R) -> Unit,
    onProgressUpdate: (P) -> Unit
) = launch {
    onPreExecute()

    val result = withContext(Dispatchers.IO) {
        doInBackground {
            withContext(Dispatchers.Main) { onProgressUpdate(it) }
        }
    }
    onPostExecute(result)
}

val Context.isConnected: Boolean
    @SuppressLint("ObsoleteSdkInt")
    get() {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        true
                    }

                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        true

                    }

                    else -> false
                }
            }

            else -> {
                // Use depreciated methods only on older devices
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                nwInfo.isConnected
            }
        }
    }