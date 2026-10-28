// ============================================================================
// Nome do arquivo: Layout.java
// Objetivo: Calcular geometria do radar e posicionamento responsivo da barra de informações.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// ============================================================================

import processing.core.PApplet;

// Geometria do radar e posição das colunas da barra de informações.
// Cada coluna reserva largura para o PIOR caso; se não couber, a fonte diminui
// e, em último caso, a distância vai para uma 2ª linha.
public class Layout {
  private final PApplet p;

  // Radar
  public float cx, cy, raio;

  // Barra de informações
  public float alturaFaixa, tamFonteInfo;
  public boolean duasLinhas;
  public float xTitulo, xAngulo, xDistIni, xDistFim;
  public float yLinha1, yLinha2;

  private int larguraAnterior = -1;
  private int alturaAnterior  = -1;

  public Layout(PApplet p) {
    this.p = p;
  }

  /** Recalcula apenas quando o tamanho da janela muda. */
  public void atualizar() {
    if (p.width == larguraAnterior && p.height == alturaAnterior) return;
    larguraAnterior = p.width;
    alturaAnterior  = p.height;

    calcularInfo();    // primeiro: define a altura da faixa
    calcularRadar();   // depois: o radar usa o espaço que sobrou
  }

  private void calcularInfo() {
    float w = p.width, h = p.height;
    float margem = w * 0.02f;
    float gap    = w * 0.03f;
    float tam    = PApplet.constrain(h * 0.035f, 14, 40);
    float wTitulo, wAngulo, wDist, total;

    while (true) {
      p.textSize(tam);
      wTitulo = p.textWidth(Config.TXT_TITULO_MAX);
      wAngulo = p.textWidth(Config.TXT_ANGULO_MAX);
      wDist   = p.textWidth(Config.TXT_DIST_MAX);
      total   = wTitulo + wAngulo + wDist + 2 * gap + 2 * margem;
      if (total <= w || tam <= 12) break;
      tam -= 1;
    }

    tamFonteInfo = tam;
    duasLinhas = (total > w);

    if (!duasLinhas) {
      alturaFaixa = PApplet.max(h * 0.07f, tam * 2.0f);
      yLinha1 = h - alturaFaixa / 2f;
      yLinha2 = yLinha1;

      xTitulo  = margem;
      xDistFim = w - margem;
      xDistIni = xDistFim - wDist;
      xAngulo  = (xTitulo + wTitulo + xDistIni) / 2f;
    } else {
      alturaFaixa = tam * 3.4f;
      yLinha1 = h - alturaFaixa * 0.72f;
      yLinha2 = h - alturaFaixa * 0.28f;

      xTitulo  = margem;
      xAngulo  = w - margem - wAngulo / 2f;
      xDistIni = margem;
      xDistFim = margem + wDist;
    }
  }

  private void calcularRadar() {
    cx = p.width / 2f;
    cy = p.height - alturaFaixa - p.height * 0.05f;

    float raioPorLargura = p.width * 0.44f;
    float raioPorAltura  = (cy - p.height * 0.06f) / 1.08f;
    raio = PApplet.max(10, PApplet.min(raioPorLargura, raioPorAltura));
  }
}
