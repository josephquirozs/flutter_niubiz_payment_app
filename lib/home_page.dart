import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

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
}
