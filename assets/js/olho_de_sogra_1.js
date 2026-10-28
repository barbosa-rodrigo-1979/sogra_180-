// ============================================================================
// Nome do arquivo: olho_de_sogra_1.js
// Objetivo: Adicionar scroll suave, menu ativo, tema claro/escuro, feedback de download e impressão.
// Desenvolvido por: Gisele Nunes, Rodrigo Barbosa
// Data: Segundo semestre de 2026
/*
===================================================================
    olho_de_sogra_1.js
    Script de suporte ao documento "Radar Ultrassônico com Arduino
    + Processing" (olho_de_sogra_1.html).
-------------------------------------------------------------------
    Funções:
      • Scroll suave para âncoras internas
      • Destaque do item ativo do menu
      • Feedback visual nos links de download
      • Alternância de tema claro/escuro (com persistência em
        localStorage) — botão flutuante #btn-tema
      • Impressão / salvamento em PDF do conteúdo — botão #btn-imprimir
===================================================================
*/

(function () {
  'use strict';

  /* =================================================================
     1. SCROLL SUAVE PARA LINKS INTERNOS (#secao-N)
     ================================================================= */
  function ativarScrollSuave() {
    document.querySelectorAll('a[href^="#"]').forEach(function (link) {
      link.addEventListener('click', function (evento) {
        var id = this.getAttribute('href');
        if (!id || id === '#' || id.length < 2) return;

        var destino = document.querySelector(id);
        if (!destino) return;

        evento.preventDefault();
        destino.scrollIntoView({ behavior: 'smooth', block: 'start' });
        if (history.pushState) history.pushState(null, '', id);
      });
    });
  }

  /* =================================================================
     2. HIGHLIGHT DO ITEM ATIVO NO MENU
     ================================================================= */
  function realcarMenuAtivo() {
    var itensMenu = document.querySelectorAll('nav a');
    if (!itensMenu.length) return;

    var caminhoAtual = window.location.pathname.split('/').pop().toLowerCase();

    itensMenu.forEach(function (link) {
      var href = (link.getAttribute('href') || '').toLowerCase();
      if (!href) return;
      var arquivoHref = href.split('/').pop();

      if (arquivoHref && arquivoHref === caminhoAtual) {
        link.classList.add('active');
        link.setAttribute('aria-current', 'page');
      } else if (
        link.classList.contains('active') &&
        arquivoHref !== caminhoAtual
      ) {
        link.classList.remove('active');
        link.removeAttribute('aria-current');
      }
    });
  }

  /* =================================================================
     3. DOWNLOAD COM FEEDBACK VISUAL
     ================================================================= */
  function ativarFeedbackDownload() {
    document.querySelectorAll('.download-link').forEach(function (link) {
      link.addEventListener('click', function () {
        var rotuloOriginal = this.textContent;
        this.textContent = '⏳ Preparando download…';
        this.style.pointerEvents = 'none';

        var self = this;
        setTimeout(function () {
          self.textContent = rotuloOriginal;
          self.style.pointerEvents = '';
        }, 1200);
      });
    });
  }

  /* =================================================================
     4. MODO CLARO / ESCURO  (persistência em localStorage)
     ================================================================= */
  var CHAVE_TEMA = 'olho_de_sogra_1_tema';

  function aplicarTema(tema) {
    var btn = document.getElementById('btn-tema');
    if (tema === 'dark') {
      document.body.classList.add('dark-mode');
      if (btn) {
        btn.textContent = '☀️';
        btn.setAttribute('aria-label', 'Alternar para modo claro');
        btn.setAttribute('title', 'Alternar para modo claro');
      }
    } else {
      document.body.classList.remove('dark-mode');
      if (btn) {
        btn.textContent = '🌙';
        btn.setAttribute('aria-label', 'Alternar para modo escuro');
        btn.setAttribute('title', 'Alternar modo escuro');
      }
    }
  }

  function inicializarTema() {
    var temaSalvo = null;
    try {
      temaSalvo = localStorage.getItem(CHAVE_TEMA);
    } catch (e) {
      /* modo privado */
    }

    // ─────────────────────────────────────────────────────────────
    // TEMA PADRÃO = ESCURO
    // Na primeira visita (sem preferência salva), o site abre em
    // modo escuro. Se o usuário já tiver escolhido "claro" antes,
    // respeita a escolha dele.
    // ─────────────────────────────────────────────────────────────
    if (!temaSalvo) {
      temaSalvo = 'dark';
    }

    aplicarTema(temaSalvo);

    var btn = document.getElementById('btn-tema');
    if (!btn) return;

    btn.addEventListener('click', function () {
      var estaEscuro = document.body.classList.contains('dark-mode');
      var novoTema = estaEscuro ? 'light' : 'dark';
      aplicarTema(novoTema);
      try {
        localStorage.setItem(CHAVE_TEMA, novoTema);
      } catch (e) {
        /* ignora */
      }
    });
  }

  /* =================================================================
     5. IMPRESSÃO / SALVAR EM PDF
     ================================================================= */
  function inicializarImpressao() {
    var btn = document.getElementById('btn-imprimir');
    if (!btn) return;

    btn.addEventListener('click', function () {
      // Dá um pequeno delay para o navegador não capturar o foco do botão
      // durante a geração da pré-visualização de impressão.
      setTimeout(function () {
        window.print();
      }, 100);
    });

    // Atalho opcional: Ctrl+P / Cmd+P continua funcionando normalmente,
    // pois o CSS @media print já esconde cabeçalho, menu, rodapé e botões.
  }

  /* =================================================================
     6. ATIVAÇÃO
     ================================================================= */
  function iniciar() {
    ativarScrollSuave();
    realcarMenuAtivo();
    ativarFeedbackDownload();
    inicializarTema();
    inicializarImpressao();

    console.info(
      '%c📡 olho_de_sogra_1.js carregado — tema e impressão ativos.',
      'color: #C99700; font-weight: bold;',
    );
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', iniciar);
  } else {
    iniciar();
  }
})();
