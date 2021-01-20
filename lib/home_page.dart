import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  HomePage({Key key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Niubiz Pago App'),
      ),
      body: Center(
        child: RaisedButton(
          child: Text('Abrir formulario de pago'),
          onPressed: null,
        ),
      ),
    );
  }
}
