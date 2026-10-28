// ============================================================================
// Nome do arquivo: SensorSerial.java
// Objetivo: Gerenciar conexão serial e processar leituras recebidas.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
// ============================================================================

import processing.core.PApplet;
import processing.serial.Serial;

// Cuida da porta serial. Depende apenas de Config, Leitura e ParserPacote.
public class SensorSerial {
  private Serial porta;

  // volatile: o serialEvent roda em outra thread, o draw() lê na thread principal
  private volatile Leitura ultima = new Leitura(0, Config.DISTANCIA_MAX);

  public boolean conectar(PApplet app) {
    String[] portas = Serial.list();
    String escolhida = Config.PORTA;

    if (escolhida.equals("")) {
      if (portas.length == 0) {
        PApplet.println("Nenhuma porta serial encontrada.");
        return false;
      }
      escolhida = portas[0];
    }

    try {
      porta = new Serial(app, escolhida, Config.BAUD);
      porta.bufferUntil(Config.DELIMITADOR_PACOTE);
      PApplet.println("Conectado em " + escolhida);
      return true;
    } 
    catch (Exception e) {
      PApplet.println("Não foi possível abrir a porta serial '" + escolhida + "': " + e.getMessage());
      PApplet.println("Portas disponíveis:");
      PApplet.printArray(portas);
      return false;
    }
  }

  public Leitura ler() {
    return ultima;
  }

  /** Chamar a partir do serialEvent do sketch. */
  public void processar(Serial p) {
    Leitura nova = ParserPacote.interpretar(p.readStringUntil(Config.DELIMITADOR_PACOTE));
    if (nova != null) ultima = nova;   // pacote inválido: mantém a última leitura válida
  }
}
