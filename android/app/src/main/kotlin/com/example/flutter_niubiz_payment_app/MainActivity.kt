package com.example.flutter_niubiz_payment_app

import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import lib.visanet.com.pe.visanetlib.VisaNet

class MainActivity : FlutterActivity() {
    private val TAG = "Payment"
    private val CHANNEL = "samples.flutter.dev/mychannel"
    private lateinit var paymentChannelResult: MethodChannel.Result

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            // Note: this method is invoked on the main thread.
            paymentChannelResult = result
            when (call.method) {
                "startPaymentActivity" -> startPaymentActivity(call.arguments)
                else -> result.notImplemented()
            }
        }
    }

    private fun startPaymentActivity(arguments: Any) {
        val paymentFormProperties = arguments as HashMap<String, Any>
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
        when (requestCode) {
            VisaNet.VISANET_AUTHORIZATION -> {
                val response = when (resultCode) {
                    RESULT_OK -> data?.extras?.getString("keySuccess")
                    else -> data?.extras?.getString("keyError")
                }
                Log.i(TAG, "Payment form response $response")
                paymentChannelResult.success(response)
            }
            else -> Log.i(TAG, "Request code not implemented")
        }
    }
}
