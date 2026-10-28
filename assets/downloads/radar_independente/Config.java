// ============================================================================
// Nome do arquivo: Config.java
// Objetivo: Centralizar constantes, cores, textos e parâmetros do radar. Sem dependência de Processing.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// ============================================================================

public final class Config {
  private Config() {}

  // ---------- Comportamento ----------
  public static final boolean TELA_CHEIA    = true;  // true = tela inteira | false = janela redimensionável
  public static final String  PORTA         = "";    // ex.: "COM3" ou "/dev/ttyUSB0". Vazio = primeira encontrada
  public static final int     BAUD          = 9600;
  public static final int     DISTANCIA_MAX = 40;    // alcance máximo do sensor em cm

  // ---------- Protocolo serial: "angulo,distancia." ----------
  public static final char DELIMITADOR_PACOTE = '.';
  public static final char SEPARADOR_CAMPOS   = ',';

  // ---------- Janela (TELA_CHEIA = false) ----------
  public static final int JANELA_LARGURA = 1280;
  public static final int JANELA_ALTURA  = 720;

  // ---------- Visual ----------
  public static final int RASTRO_ALFA = 4;           // 0-255: maior = rastro some mais rápido

  public static final int COR_RADAR      = 0xFF62F51F;  // verde
  public static final int COR_VARREDURA  = 0xFF1EFA3C;  // verde
  public static final int COR_OBJETO     = 0xFFFF0A0A;  // vermelho
  public static final int COR_SEM_OBJETO = 0xFFF5621F;  // laranja

  // ---------- Textos ----------
  public static final String TXT_TITULO     = "Radar Arduino";
  public static final String TXT_ANGULO     = "Angulo: ";
  public static final String TXT_DISTANCIA  = "Distancia:";
  public static final String TXT_SEM_OBJETO = "--";

  // Piores casos, para reservar largura das colunas da barra de info
  public static final String TXT_TITULO_MAX = "Radar Arduino";
  public static final String TXT_ANGULO_MAX = "Angulo: 180 °";
  public static final String TXT_DIST_MAX   = "Distancia: 999 cm";
}
