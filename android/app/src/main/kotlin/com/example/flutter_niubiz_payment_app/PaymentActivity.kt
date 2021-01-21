package com.example.flutter_niubiz_payment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import lib.visanet.com.pe.visanetlib.VisaNet
import lib.visanet.com.pe.visanetlib.presentation.custom.VisaNetViewAuthorizationCustom

class PaymentActivity : AppCompatActivity() {
    private val TAG = "Payment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openPaymentForm()
    }

    private fun openPaymentForm() {
        val data = HashMap<String, Any>()
        data[VisaNet.VISANET_CHANNEL] = VisaNet.Channel.MOBILE
        data[VisaNet.VISANET_COUNTABLE] = true
        data[VisaNet.VISANET_SECURITY_TOKEN] =
                "eyJraWQiOiJmWk1tV3pZR0RBckxHektvalNCK2w3SjFhMnNPXC9zQnNwOTlNNmNuM3F5MD0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0NzFmYjk3Yy0xNjNlLTQyYjYtOGI3OC03Zjk1YjA1OGM1NTgiLCJldmVudF9pZCI6ImViODFhMzVjLTZkYWItNGRhYi05YTZhLTU3YTU3NzA0MjkwYiIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2MTEyNzE0MDgsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0xXzJjSjFTZTFmSSIsImV4cCI6MTYxMTI3NTAwOCwiaWF0IjoxNjExMjcxNDA4LCJqdGkiOiJhNDQwZGMwMC00ZjE5LTQ4MWUtODk0MS1mMDlmYjViYmFhOTgiLCJjbGllbnRfaWQiOiIxMGx2MDYxN281ZGljNTFlYnNucWVpaWpiNyIsInVzZXJuYW1lIjoiaW50ZWdyYWNpb25lc0BuaXViaXouY29tLnBlIn0.c6qqc5tQdAzko8FIG32miuKc9h11ARICpzIOPJrkONkK14opAatbMn4XKLtIQp_c86LndsLjQsdZp4Hezoci9zJxw36OXnGfvJIrcmZhWXC0CmUcUK2aXw8LtZYqJzjKSK7QEecpxf4mHiLebZ62yV9BacG6e7w-3mE-jNsqe9Wfn_rKu0gsMqOGLFN2f7dMYTfPmUkp0bg-TsHXZl_e3TX5TjX03vqYmsl3mdV0Lvh3JoOlLsAeEFViwvVAapX32e1NoBIIv-UuKlyUazD_Tb9XOcQLsc21YZCpNpCfzd1TPh8JCAHpHAdh2gTFJnHuxq_CsB8SKSiNFDoA1QB_8w"
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

        val custom = VisaNetViewAuthorizationCustom()
        custom.logoImage = R.drawable.tulogo
        custom.buttonColorMerchant = R.color.visanet_black

        try {
            VisaNet.authorization(this@PaymentActivity, data, custom)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i(TAG, "onClick: " + e.message)
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