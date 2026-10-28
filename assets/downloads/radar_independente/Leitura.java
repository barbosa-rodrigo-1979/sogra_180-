// ============================================================================
// Nome do arquivo: Leitura.java
// Objetivo: Modelar uma leitura imutável de ângulo e distância. Sem dependência de Processing.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// ============================================================================

public final class Leitura {
  public final int angulo;
  public final int distancia;

  public Leitura(int angulo, int distancia) {
    this.angulo = angulo;
    this.distancia = distancia;
  }

  public boolean temObjeto() {
    return distancia < Config.DISTANCIA_MAX;
  }
}
