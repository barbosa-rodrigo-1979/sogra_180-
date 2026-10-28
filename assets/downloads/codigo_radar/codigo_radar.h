// ============================================================================
// Nome do arquivo: codigo_radar.h
// Objetivo: Declarar pinos, protótipo de calculateDistance() e extern do servo.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// Contém APENAS declarações:
//   - Constantes de pinos
//   - Protótipo de calculateDistance()
//   - Declaração extern do objeto Servo
// As implementações ficam em codigo_radar.cpp, e o fluxo principal
// (setup/loop) fica em codigo_radar.ino.
// ============================================================================

#ifndef CODIGO_RADAR_H
#define CODIGO_RADAR_H

#include <Arduino.h>
#include <Servo.h>

// --- Pinos do sensor ultrassônico HC-SR04 ---
const int trigPin = 10;   // Pino de disparo (Trigger)
const int echoPin = 11;   // Pino de eco (Echo)

// --- Pino de sinal do servo motor ---
const int servoPin = 12;  // Sinal PWM do servo SG90

// --- Objeto global do servo ---
// Declarado como extern: a definição real (alocação de memória)
// está em codigo_radar.cpp.
extern Servo myServo;

// --- Protótipo da função de medição ---
// Retorna a distância em cm (2–400) se a leitura for válida, ou -1 se falhar.
int calculateDistance();

#endif // CODIGO_RADAR_H
