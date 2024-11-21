import 'package:flutter/material.dart';
import 'package:lab/components/game_field/game_field.dart';

import '../../components/ship/warship_painter.dart';

class BattlePreparationScreen extends StatefulWidget {
  const BattlePreparationScreen({super.key});

  @override
  State<BattlePreparationScreen> createState() => _BattlePreparationScreenState();
}

class _BattlePreparationScreenState extends State<BattlePreparationScreen> {
  final List<Ship> ships = [
    Ship(shipSize: 4, isHorizontal: true),
    Ship(shipSize: 3, isHorizontal: true),
    Ship(shipSize: 3, isHorizontal: true),
    Ship(shipSize: 2, isHorizontal: true),
    Ship(shipSize: 2, isHorizontal: true),
    Ship(shipSize: 2, isHorizontal: true),
    Ship(shipSize: 1, isHorizontal: true),
    Ship(shipSize: 1, isHorizontal: true),
    Ship(shipSize: 1, isHorizontal: true),
    Ship(shipSize: 1, isHorizontal: true),
  ];
  Ship get currentShip => _currentShip ?? ships.first;
  set currentShip(Ship ship) {
    _currentShip = ship;
  }
  Ship? _currentShip;

  int _currentShipId = 0;
  @override
  void initState() {
    super.initState();
    _currentShip = ships.first;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor:Colors.white,
      appBar: AppBar(
        backgroundColor: const Color.fromARGB(255, 9, 41, 104),
      ),
      body: Center(
          child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                const GameFieldWidget(),
                const SizedBox(height: 30),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      onPressed: () {
                        if((_currentShipId > 0)){
                          setState(() {
                            currentShip = ships[--_currentShipId];
                          });
                        }
                      },
                      child: const Text("Prev"),
                    ),
                    const SizedBox(width: 15),
                    ElevatedButton(
                      onPressed: () {
                        setState(() {
                          currentShip.isHorizontal = !currentShip.isHorizontal;
                        });
                      },
                      child: const Text("Rotate Ship"),
                    ),
                    const SizedBox(width: 15),
                    ElevatedButton(
                      onPressed: () {
                        if(_currentShipId < ships.length){
                          setState(() {
                            currentShip = ships[++_currentShipId];
                          });
                        }
                      },
                      child: const Text("Next"),
                    ),
                  ],
                ),
                const SizedBox(height: 30),
                AnimatedSwitcher(
                    duration: const Duration(milliseconds: 500), // продолжительность анимации
                    transitionBuilder: (Widget child, Animation<double> animation) {
                      return ScaleTransition(
                        scale: animation,
                        child: child,
                      );
                    },
                    child: CustomPaint(
                      key: ValueKey<int>(_currentShipId), // уникальный ключ для каждого корабля
                      size: const Size(100, 100),
                      painter: WarshipPainterWidget(ship: currentShip),
                    ),
                )
              ]
          ),
        ),
      );
  }
}