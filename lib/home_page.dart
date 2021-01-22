import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;

class HomePage extends StatefulWidget {
  HomePage({Key key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  static const channel = const MethodChannel('samples.flutter.dev/mychannel');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Niubiz Pago App'),
      ),
      body: Center(
        child: RaisedButton(
          child: Text('Abrir formulario de pago'),
          onPressed: () => _openPaymentForm(),
        ),
      ),
    );
  }

  Future<void> _openPaymentForm() async {
    print('Opening payment form');
    try {
      await channel.invokeMethod('startPaymentActivity');
    } on PlatformException catch (e, st) {
      print(e);
      print(st);
    }
  }

  Future<String> _getSecurityToken() async {
    final username = 'integraciones@niubiz.com.pe';
    final password = '_7z3@8fF';
    final credentials = base64.encode(utf8.encode('$username:$password'));
    final response = await http.get(
      'https://apisandbox.vnforappstest.com/api.security/v1/security',
      headers: {'Authorization': 'Basic $credentials}'},
    );
    print('Response status ${response.statusCode}');
    print('Response body ${response.body}');
    return json.decode(response.body);
  }
}
