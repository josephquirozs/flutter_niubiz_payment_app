package com.example.flutter_niubiz_payment_app

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import lib.visanet.com.pe.visanetlib.VisaNet
import lib.visanet.com.pe.visanetlib.presentation.custom.VisaNetViewAuthorizationCustom

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
        val data = HashMap<String, Any>()
        data[VisaNet.VISANET_CHANNEL] = VisaNet.Channel.MOBILE
        data[VisaNet.VISANET_COUNTABLE] = true
        data[VisaNet.VISANET_SECURITY_TOKEN] =
                "eyJraWQiOiJmWk1tV3pZR0RBckxHektvalNCK2w3SjFhMnNPXC9zQnNwOTlNNmNuM3F5MD0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0NzFmYjk3Yy0xNjNlLTQyYjYtOGI3OC03Zjk1YjA1OGM1NTgiLCJldmVudF9pZCI6IjdlYWJiYTUyLTk2MmEtNDg3ZC05Njg0LWJiNWNjZTQ0NDk2ZiIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2MTEzMzU1ODYsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0xXzJjSjFTZTFmSSIsImV4cCI6MTYxMTMzOTE4NiwiaWF0IjoxNjExMzM1NTg2LCJqdGkiOiIxZjQwOWI2Yi1lNzM1LTQ2Y2MtODc1NS0xNjcyZTQ1ZTEzOGMiLCJjbGllbnRfaWQiOiIxMGx2MDYxN281ZGljNTFlYnNucWVpaWpiNyIsInVzZXJuYW1lIjoiaW50ZWdyYWNpb25lc0BuaXViaXouY29tLnBlIn0.BHPcsRzhQlSX9STFYaoqsCtIcQUoDIaDXUBzdWEiItX_kRMaxrZ224oI4M0fayLdc8MrrM8ABJMRhGQ4mtWbKqTLMpfwPC4kDAzOQxWs2Mrc6w-7mKTAp9M96z-syBTFFIsIttdKTOIk58Bkl4b1z5oHjOlhA-fNdp3r2mKMYgokcLyQ3bqMK6nN6ELB5H3olbv-s6Lo9IBopI0FKA_Gtrr9a7m9BqZXuC4PqU3NmZqmtAOzrRjT0way__p76qqyS8Nl8gIxfqkbZtpkiHcFEcAKH_O4mkfxErB4_aa9SXfiicZpnkOfnTBO4gcjd2il_GVcWcWmTBW9ynReppV6rQ"
        data[VisaNet.VISANET_MERCHANT] = "456879852"
        data[VisaNet.VISANET_PURCHASE_NUMBER] = "1790"
        data[VisaNet.VISANET_AMOUNT] = "15.22"
        data[VisaNet.VISANET_ENDPOINT_URL] = "https://apisandbox.vnforappstest.com/"
        data[VisaNet.VISANET_CERTIFICATE_HOST] = "apisandbox.vnforappstest.com"
        data[VisaNet.VISANET_CERTIFICATE_PIN] =
                "sha256/lmxiL6uol7hb4UwDxtk2qbF2lBnJc7zqZRT6sFfYWEE="
        data[VisaNet.VISANET_REGISTER_NAME] = "Juan";
        data[VisaNet.VISANET_REGISTER_LASTNAME] = "Perez";
        data[VisaNet.VISANET_REGISTER_EMAIL] = "jperez@test.com";

//        val custom = VisaNetViewAuthorizationCustom()
//        custom.logoImage = R.drawable.tulogo
//        custom.buttonColorMerchant = R.color.visanet_black

        try {
            VisaNet.authorization(this@MainActivity, data)
//            VisaNet.authorization(this@MainActivity, data, custom)
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
