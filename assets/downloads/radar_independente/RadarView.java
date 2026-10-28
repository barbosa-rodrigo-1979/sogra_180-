// ============================================================================
// Nome do arquivo: RadarView.java
// Objetivo: Desenhar grade, varredura e objeto detectado no radar.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// ============================================================================

import processing.core.PApplet;
import processing.core.PConstants;

// Desenha grade, varredura e objeto. Recebe o PApplet por injeção.
public class RadarView {
  private final PApplet p;

  public RadarView(PApplet p) {
    this.p = p;
  }

  public void desenhar(Layout l, Leitura leitura) {
    desenharGrade(l);
    desenharVarredura(l, leitura);
    desenharObjeto(l, leitura);
  }

  private void desenharGrade(Layout l) {
    float tamLabel = PApplet.constrain(p.height * 0.022f, 10, 24);

    p.pushMatrix();
    p.translate(l.cx, l.cy);

    p.noFill();
    p.strokeWeight(2);
    p.stroke(Config.COR_RADAR);

    for (int k = 1; k <= 4; k++) {
      float d = 2 * l.raio * k / 4f;
      p.arc(0, 0, d, d, PConstants.PI, PConstants.TWO_PI);
    }

    p.line(-l.raio, 0, l.raio, 0);
    for (int a = 30; a <= 150; a += 30) {
      linhaPolar(0, l.raio, a);
    }

    // Rótulos de distância
    p.fill(Config.COR_RADAR);
    p.noStroke();
    p.textSize(tamLabel);
    p.textAlign(PConstants.CENTER, PConstants.TOP);
    for (int k = 1; k <= 4; k++) {
      int cm = Config.DISTANCIA_MAX * k / 4;
      p.text(cm + "cm", l.raio * k / 4f, 6);
    }

    // Rótulos de ângulo
    p.textAlign(PConstants.CENTER, PConstants.CENTER);
    float r = l.raio * 1.06f;
    for (int a = 30; a <= 150; a += 30) {
      float rad = PApplet.radians(a);
      p.text(a + "°", r * PApplet.cos(rad), -r * PApplet.sin(rad));
    }

    p.popMatrix();
  }

  private void desenharVarredura(Layout l, Leitura leitura) {
    p.pushMatrix();
    p.translate(l.cx, l.cy);
    p.strokeWeight(espessura(l));
    p.stroke(Config.COR_VARREDURA);
    linhaPolar(0, l.raio, leitura.angulo);
    p.popMatrix();
  }

  private void desenharObjeto(Layout l, Leitura leitura) {
    if (!leitura.temObjeto()) return;

    float pix = leitura.distancia * l.raio / Config.DISTANCIA_MAX;

    p.pushMatrix();
    p.translate(l.cx, l.cy);
    p.strokeWeight(espessura(l));
    p.stroke(Config.COR_OBJETO);
    linhaPolar(pix, l.raio, leitura.angulo);
    p.popMatrix();
  }

  private float espessura(Layout l) {
    return PApplet.max(3, l.raio * 0.012f);
  }

  // Linha em coordenadas polares (a partir da origem já transladada)
  private void linhaPolar(float r1, float r2, float anguloGraus) {
    float rad = PApplet.radians(anguloGraus);
    float c = PApplet.cos(rad), s = PApplet.sin(rad);
    p.line(r1 * c, -r1 * s, r2 * c, -r2 * s);
  }
}
