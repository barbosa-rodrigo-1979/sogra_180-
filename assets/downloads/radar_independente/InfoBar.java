// ============================================================================
// Nome do arquivo: InfoBar.java
// Objetivo: Desenhar a barra inferior com título, ângulo e distância.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// ============================================================================
import processing.core.PApplet;
import processing.core.PConstants;

// Barra inferior: título, ângulo e distância.
public class InfoBar {
  private final PApplet p;

  public InfoBar(PApplet p) {
    this.p = p;
  }

  public void desenhar(Layout l, Leitura leitura) {
    // Faixa opaca: apaga o texto do frame anterior
    p.noStroke();
    p.fill(0);
    p.rect(0, p.height - l.alturaFaixa, p.width, l.alturaFaixa);

    p.textSize(l.tamFonteInfo);
    p.fill(Config.COR_RADAR);

    p.textAlign(PConstants.LEFT, PConstants.CENTER);
    p.text(Config.TXT_TITULO, l.xTitulo, l.yLinha1);

    p.textAlign(PConstants.CENTER, PConstants.CENTER);
    p.text(Config.TXT_ANGULO + leitura.angulo + " °", l.xAngulo, l.yLinha1);

    p.textAlign(PConstants.LEFT, PConstants.CENTER);
    p.text(Config.TXT_DISTANCIA, l.xDistIni, l.yLinha2);

    String valor;
    if (leitura.temObjeto()) {
      valor = leitura.distancia + " cm";
      p.fill(Config.COR_RADAR);
    } else {
      valor = Config.TXT_SEM_OBJETO;
      p.fill(Config.COR_SEM_OBJETO);
    }
    p.textAlign(PConstants.RIGHT, PConstants.CENTER);
    p.text(valor, l.xDistFim, l.yLinha2);
  }
}
