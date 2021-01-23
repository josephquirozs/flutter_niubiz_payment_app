import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_niubiz_payment_app/credentials.dart';
import 'package:flutter_niubiz_payment_app/payment_form_properties.dart';
import 'package:http/http.dart' as http;

class HomePage extends StatefulWidget {
  HomePage({Key key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  static const channel = const MethodChannel('samples.flutter.dev/mychannel');
  var _callingPaymentForm = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Niubiz Pago App'),
      ),
      body: Center(
        child: SizedBox(
          width: 300,
          height: 50,
          child: RaisedButton(
            child: _callingPaymentForm
                ? CircularProgressIndicator()
                : Text('Abrir formulario de pago'),
            onPressed: () => _openPaymentForm(),
          ),
        ),
      ),
    );
  }

  Future<void> _openPaymentForm() async {
    print('Opening payment form');
    _callingPaymentForm = true;
    setState(() {});
    try {
      final myPaymentFormProperties = paymentFormProperties;
      myPaymentFormProperties['securityToken'] = await _getSecurityToken();
      myPaymentFormProperties['purchaseNumber'] = '1790';
      myPaymentFormProperties['amount'] = '15.22';
      myPaymentFormProperties['registerName'] = 'Juan';
      myPaymentFormProperties['registerLastname'] = 'Perez';
      myPaymentFormProperties['registerEmail'] = 'jperez@test.com';
      await channel.invokeMethod(
          'startPaymentActivity', myPaymentFormProperties);
    } catch (e, st) {
      print(e);
      print(st);
    } finally {
      _callingPaymentForm = false;
      setState(() {});
    }
  }

  Future<String> _getSecurityToken() async {
    final encodedCredentials = base64.encode(
        utf8.encode('${credentials['username']}:${credentials['password']}'));
    final response = await http.get(
      'https://apisandbox.vnforappstest.com/api.security/v1/security',
      headers: {'Authorization': 'Basic $encodedCredentials'},
    );
    print('Response status ${response.statusCode}');
    print('Response body ${response.body}');
    return response.body;
  }
}
