// ============================================================================
// Nome do arquivo: ParserPacote.java
// Objetivo: Converter o pacote serial "angulo,distancia." em Leitura. Java puro.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// ============================================================================

import java.util.regex.Pattern;

// Converte texto "angulo,distancia." em Leitura. Java puro: pode ser testado sem Processing.
public final class ParserPacote {
  private ParserPacote() {}

  private static final String SEP = Pattern.quote(String.valueOf(Config.SEPARADOR_CAMPOS));

  /** Retorna a Leitura, ou null se o pacote for inválido/corrompido. */
  public static Leitura interpretar(String pacote) {
    if (pacote == null) return null;

    String dado = pacote.trim();
    if (dado.length() > 0 && dado.charAt(dado.length() - 1) == Config.DELIMITADOR_PACOTE) {
      dado = dado.substring(0, dado.length() - 1).trim();
    }

    String[] partes = dado.split(SEP, -1);
    if (partes.length != 2) return null;

    try {
      int angulo = Math.max(0, Math.min(180, Integer.parseInt(partes[0].trim())));
      int distancia = Integer.parseInt(partes[1].trim());
      if (distancia < 0) return null;
      return new Leitura(angulo, distancia);
    } 
    catch (NumberFormatException e) {
      return null;
    }
  }
}
