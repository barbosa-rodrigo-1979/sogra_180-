// ============================================================================
// Nome do arquivo: codigo_radar.ino
// Objetivo: Implementar setup() e loop() da varredura do radar ultrassônico.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// Hardware:
//   - HC-SR04 : trig = 10, echo = 11
//   - Servo SG90 : sinal = 12
//// Saída serial: "<angulo>,<distancia>." para cada ponto da varredura
// A implementação de calculateDistance() está em codigo_radar.cpp.
// As declarações (pinos, protótipo, extern do servo) estão em codigo_radar.h.
// ============================================================================

#include "codigo_radar.h"

// ---------------------------------------------------------------------------
// SETUP: configuração inicial executada uma única vez
// ---------------------------------------------------------------------------
void setup() {
  // trigPin como saída: o Arduino gera o pulso de disparo
  pinMode(trigPin, OUTPUT);
  // echoPin como entrada: o sensor devolve o pulso de eco
  pinMode(echoPin, INPUT);

  // Comunicação serial a 9600 bps (mesma taxa usada pelo Processing)
  Serial.begin(9600);

  // Associa o objeto servo ao pino físico definido no header
  myServo.attach(servoPin);

  // Centraliza o servo em 90° antes de iniciar a varredura,
  // evitando um "tranco" brusco a partir de posição indefinida.
  myServo.write(90);
  delay(300);  // Tempo para o SG90 atingir o centro
}

// ---------------------------------------------------------------------------
// LOOP: varredura contínua de 15° a 165° e de volta
// ---------------------------------------------------------------------------
void loop() {
  // ===== VARREDURA DE IDA: 15° → 165° (incremento de 1°) =====
  for (int i = 15; i <= 165; i++) {
    myServo.write(i);

    // Aguarda estabilização mecânica do servo antes de medir.
    // SG90: ~1,7 ms/grau no pior caso; 30 ms cobre passos de 1° com folga.
    delay(30);

    int d = calculateDistance();

    // Só envia dados válidos (d > 0). Valores -1 indicam leitura inválida.
    if (d > 0) {
      Serial.print(i);       // ângulo em graus
      Serial.print(",");     // separador
      Serial.print(d);       // distância em cm
      Serial.print(".");     // marcador de fim de amostra (usado pelo Processing)
    }
  }

  // ===== VARREDURA DE VOLTA: 165° → 15° (decremento de 1°) =====
  for (int i = 165; i > 15; i--) {
    myServo.write(i);
    delay(30);
    int d = calculateDistance();

    if (d > 0) {
      Serial.print(i);
      Serial.print(",");
      Serial.print(d);
      Serial.print(".");
    }
  }
}
