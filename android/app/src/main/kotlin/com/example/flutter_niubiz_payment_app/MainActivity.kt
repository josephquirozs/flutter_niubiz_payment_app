package com.example.flutter_niubiz_payment_app

import android.content.Intent
import android.widget.Toast
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import lib.visanet.com.pe.visanetlib.VisaNet

class MainActivity : FlutterActivity() {
    private val CHANNEL = "samples.flutter.dev/mychannel"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            // Note: this method is invoked on the main thread.
            val paymentFormProperties = call.arguments as HashMap<String, Any>
            when (call.method) {
                "startPaymentActivity" -> {
                    startPaymentActivity(paymentFormProperties)
                    result.success("Success")
                }
                else -> {
                    result.notImplemented()
                }
            }
        }
    }

    private fun startPaymentActivity(paymentFormProperties: HashMap<String, Any>) {
//        val custom = VisaNetViewAuthorizationCustom()
//        custom.logoImage = R.drawable.tulogo
//        custom.buttonColorMerchant = R.color.visanet_black
        try {
            VisaNet.authorization(this@MainActivity, paymentFormProperties)
//            VisaNet.authorization(this@MainActivity, paymentFormProperties, custom)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VisaNet.VISANET_AUTHORIZATION) {
            // TODO: Implementar respuesta del formulario
        } else {
            Toast.makeText(applicationContext, "Unauthorized", Toast.LENGTH_LONG).show()
        }
    }
}
