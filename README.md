Sogra 180º - Radar Ultrassônico com Arduino e Processing
Visão Geral

O Projeto Sogra 180º é um sistema embarcado didático que integra um Arduino Uno, um sensor ultrassônico HC-SR04 e um servo motor SG90 a uma aplicação desktop desenvolvida em Processing, criando uma visualização em tempo real do tipo radar. O servo varre o sensor de 15° a 165° (e de volta), medindo a distância dos obstáculos à frente, enquanto o Processing recebe os dados via porta serial e renderiza uma interface gráfica em coordenadas polares, com rastro de varredura verde e detecção do objeto mais próximo destacada em vermelho.
Estrutura Técnica

O projeto está dividido em dois lados independentes:

    Lado Arduino (firmware em C++): modularizado em três arquivos (codigo_radar.ino, .h e .cpp), realiza o disparo do ultrassom (pulseIn com timeout de 30 ms), move o servo, descarta leituras inválidas e envia pacotes no formato "ângulo,distância." a 9600 bps.

    Lado Processing (visualização em Java): organizado em classes com responsabilidades bem definidas (Config, Leitura, ParserPacote, SensorSerial, Layout, RadarView, InfoBar, Janela), recebe os pacotes com bufferUntil('.'), trata falhas de serial sem derrubar o sketch, e desenha um radar responsivo com grade, varredura e barra de informações.

Caráter Pedagógico

O material é um documento didático da fase 1, escrito em tom irreverente, voltado ao ensino de robótica no contexto da UTFPR – Campus Siqueira Campos. Cada trecho de código é explicado linha por linha, com conexões explícitas a habilidades da BNCC (EM13CO01, EM13CO12, EM13CO16, EF05MA19 e Competência Geral 5 – Cultura Digital), cobrindo desde a física do ultrassom (d = (t × 0,034) / 2) até geometria polar, proporcionalidade e design de interface.
Autoria

Desenvolvido por Gisele Nunes e Rodrigo Barbosa (segundo semestre de 2026), com versões completas dos códigos disponibilizadas para download e materiais complementares em projetosgn.com.br e projetosrb.com.br.
