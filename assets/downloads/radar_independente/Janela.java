// ============================================================================
// Nome do arquivo: Janela.java
// Objetivo: Controlar tamanho e posição da janela em modo não fullscreen.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// ============================================================================
import processing.core.PApplet;

public class Janela {
  private final PApplet p;
  private boolean maximizada = false;

  public Janela(PApplet p) {
    this.p = p;
  }

  public void alternarMaximizada() {
    maximizada = !maximizada;
    if (maximizada) {
      p.getSurface().setSize(p.displayWidth, p.displayHeight);
      p.getSurface().setLocation(0, 0);
    } else {
      p.getSurface().setSize(Config.JANELA_LARGURA, Config.JANELA_ALTURA);
      p.getSurface().setLocation(100, 100);
    }
    p.background(0);
  }
}
