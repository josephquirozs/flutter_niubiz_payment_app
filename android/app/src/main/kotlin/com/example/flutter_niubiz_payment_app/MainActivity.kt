package com.example.flutter_niubiz_payment_app

import android.content.Intent
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val CHANNEL = "samples.flutter.dev/mychannel"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            // Note: this method is invoked on the main thread.
            if (call.method == "startPaymentActivity") {
                startPaymentActivity()
                result.success("Success")
            } else {
                result.notImplemented()
            }
        }
    }

    private fun startPaymentActivity() {
        val intent = Intent(this, PaymentActivity::class.java)
        startActivity(intent)
    }
}
