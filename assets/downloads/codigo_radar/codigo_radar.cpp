// ============================================================================
// Nome do arquivo: codigo_radar.cpp
// Objetivo: Implementar calculateDistance() e definir o objeto global Servo myServo.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// Contém:
//   - Definição do objeto global Servo myServo
//   - Implementação da função calculateDistance()
// ============================================================================

#include "codigo_radar.h"

// --- Definição real do objeto global do servo (a declaração extern está no .h) ---
Servo myServo;

// ---------------------------------------------------------------------------
// calculateDistance(): dispara o HC-SR04 e retorna a distância em cm
//
// Retorno:
//   - 2 a 400  : distância válida em centímetros
//   - -1       : leitura inválida (sem eco ou fora da faixa do sensor)
// ---------------------------------------------------------------------------
int calculateDistance() {
  // Garante nível baixo estável antes do pulso de disparo
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);

  // Pulso de disparo de 10 µs (requisito do HC-SR04)
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  // Mede a largura do pulso de eco com timeout de 30 ms (~5 m de ida e volta).
  // Sem timeout, pulseIn() bloquearia por ~1 s quando não houvesse eco.
  long duration = pulseIn(echoPin, HIGH, 30000);

  // duration == 0 → nenhum eco dentro do timeout → leitura inválida
  if (duration == 0) {
    return -1;
  }

  // Fórmula da distância: d = (t × v) / 2, com v = 0,034 cm/µs
  int dist = (int)(duration * 0.034 / 2.0);

  // Filtro de faixa válida do HC-SR04 (2–400 cm)
  if (dist < 2 || dist > 400) {
    return -1;
  }

  return dist;
}
