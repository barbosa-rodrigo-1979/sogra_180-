// ============================================================
// Nome do arquivo: radar_independente.pde
// Objetivo: Integrar e executar o sketch principal do radar no Processing.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
//
// Radar Arduino (sensor ultrassônico + servo) — sketch principal
//
// Este arquivo só "liga" as peças. Cada classe está em seu próprio
// arquivo .java e não enxerga variáveis do sketch:
//   Config.java        constantes, cores e textos
//   Leitura.java       modelo de dados (ângulo + distância)
//   ParserPacote.java  texto "angulo,distancia." -> Leitura (Java puro)
//   SensorSerial.java  porta serial
//   Layout.java        geometria e posicionamento
//   RadarView.java     desenho do radar
//   InfoBar.java       desenho da barra de informações
//   Janela.java        maximizar/restaurar janela
// ============================================================

import processing.serial.*;

SensorSerial sensor;
Layout       layout;
RadarView    radar;
InfoBar      infoBar;
Janela       janela;

void settings() {
  if (Config.TELA_CHEIA) {
    fullScreen();            // para P3D/P2D use fullScreen(P3D) / fullScreen(P2D)
  } else {
    size(Config.JANELA_LARGURA, Config.JANELA_ALTURA);
  }
}

void setup() {
  if (!Config.TELA_CHEIA) {
    surface.setResizable(true);
  }
  background(0);

  sensor  = new SensorSerial();
  layout  = new Layout(this);
  radar   = new RadarView(this);
  infoBar = new InfoBar(this);
  janela  = new Janela(this);

  sensor.conectar(this);
}

void draw() {
  Leitura leitura = sensor.ler();   // uma leitura consistente por frame

  // Efeito de rastro (fade)
  noStroke();
  fill(0, Config.RASTRO_ALFA);
  rect(0, 0, width, height);

  layout.atualizar();
  radar.desenhar(layout, leitura);
  infoBar.desenhar(layout, leitura);   // por último, sobre fundo opaco
}

// Callbacks do Processing: precisam ficar no sketch
void serialEvent(Serial p) {
  sensor.processar(p);
}

// Em modo janela, F alterna maximizada. Em fullScreen(), ESC fecha o programa.
void keyPressed() {
  if (!Config.TELA_CHEIA && (key == 'f' || key == 'F')) {
    janela.alternarMaximizada();
  }
}
