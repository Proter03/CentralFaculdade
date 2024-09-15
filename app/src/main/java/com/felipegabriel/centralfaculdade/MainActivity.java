package com.felipegabriel.centralfaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Docente;
import com.felipegabriel.centralfaculdade.domain.Sessao;
import com.felipegabriel.centralfaculdade.domain.Termo;
import com.felipegabriel.centralfaculdade.domain.Usuario;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaNota;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaTermo;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.CursoDisciplina;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.Grade;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.DisciplinaTermoProva;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.DocenteDisciplinaTermo;
import com.felipegabriel.centralfaculdade.repository.GenericDatabase;
import com.felipegabriel.centralfaculdade.service.AlunoDisciplinaNotaService;
import com.felipegabriel.centralfaculdade.service.AlunoService;
import com.felipegabriel.centralfaculdade.service.CursoService;
import com.felipegabriel.centralfaculdade.service.DisciplinaService;
import com.felipegabriel.centralfaculdade.service.GradeCurricularService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainActivity extends AppCompatActivity {
    private GenericDatabase<Usuario> tableUsuario;
    private DisciplinaService disciplinaService;
    private AlunoService alunoService;
    private CursoService cursoService;
    private AlunoDisciplinaNotaService alunoDisciplinaNotaService;
    private GradeCurricularService gradeCurricularService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alunoService = new AlunoService(this);
        cursoService = new CursoService(this);
        disciplinaService = new DisciplinaService(this);
        alunoDisciplinaNotaService = new AlunoDisciplinaNotaService(this);
        gradeCurricularService = new GradeCurricularService(this);

        init();
    }

    private void init() {
        getGenericDatabase();

        criaUsuario();

        criaAluno();

        criaCurso();

        criaDisciplina();
        
        criaNota();

        criaGradeCurricular();
    }

    private void criaGradeCurricular() {
    }

    private void criaNota() {
        Curso curso = cursoService.getCursoByDescricao("Ciência da Computação");
        Aluno aluno = alunoService.buscaAluno(Sessao.getId());
        Disciplina disciplina = disciplinaService.getDisciplinaByNome("ALGORITMOS E ESTRUTURAS DE DADOS I");

        if (curso == null || aluno == null || disciplina == null) {
            throw new RuntimeException("Erro criar nota");
        }

        AlunoDisciplinaNota alunoDisciplinaNota = alunoDisciplinaNotaService.getIdAlunoAndIdDisciplina(aluno.getId(), disciplina.getId(), curso.getId());
        if (alunoDisciplinaNota == null) {
            alunoDisciplinaNotaService.criaAlunoDisciplinaNota(aluno.getId(), disciplina.getId(), curso.getId(), 8);
        }
    }

    private void criaDisciplina() {
        if (disciplinaService.getCountAllRegistro() == 0L) {
            disciplinaService.criaDisciplina("ALGORITMOS E ESTRUTURAS DE DADOS I", "Lógica. Desenvolvimento de algoritmos. Linguagem estruturada.Operadores: atribuição, aritméticos, relacionais e lógicos. Estruturasde programação: Decisão e repetição. Estruturas de dadoshomogêneas unidimensionais e multidimensionais utilizando alinguagem C/C++.", 120.0);
            disciplinaService.criaDisciplina("APLICAÇÕES WEB I", "Linguagens e Tecnologias Html, Dhtml, Xhtml, Html5, CSS, Xml",40.0);
            disciplinaService.criaDisciplina("CÁLCULO DIFERENCIAL E INTEGRAL I", "Funções, Limite, Continuidade e Derivadas. Estudo da variação das funções.", 80.0);
            disciplinaService.criaDisciplina("FUNDAMENTOS DE MATEMÁTICA ELEMENTAR I", "Conjuntos. Funções elementares. Função exponencial.",40.0);
            disciplinaService.criaDisciplina("GEOMETRIA ANALÍTICA E VETORES I", "Matrizes. Sistemas Lineares. Vetores.",40.0);
            disciplinaService.criaDisciplina("INTRODUÇÃO À CIÊNCIA DA COMPUTAÇÃO", "Origem e Histórico dos Computadores, Microprocessadores, Estrutura e Organização da Informação, Linguagens de Programação, Sistemas Operacionais.",40.0);
            disciplinaService.criaDisciplina("LÓGICA PARA COMPUTAÇÃO I", "Sistemas Dicotômicos. Sistemas de Numeração. Conversão entre os Sistemas de Numeração. Proposições. Operações Lógicas sobre Proposições. Construção de Tabelas-Verdade. Relação de Implicação e de Equivalência.",40.0);
            disciplinaService.criaDisciplina("ALGORITMOS E ESTRUTURAS DE DADOS II", "Modularização. Passagem de Parâmetro por valor e referência. Ponteiro. Estruturas de dados: Registros e Arquivos. Recursividade utilizando a linguagem C/C++.", 120.0);
            disciplinaService.criaDisciplina("APLICAÇÕES WEB II", "Javascript, jQuery, jSon, Ajax. Introdução à Linguagem de Programação para Web (C# .NET ou PHP).",80.0);
            disciplinaService.criaDisciplina("CÁLCULO DIFERENCIAL E INTEGRAL II", "Funções deriváveis. Aplicações da derivada. Integração. Métodos de integração. Aplicação da integral definida.", 80.0);
            disciplinaService.criaDisciplina("FUNDAMENTOS DE MATEMÁTICA ELEMENTAR II", "Logaritmos. Trigonometria. Progressões Aritméticas e Geométricas.",40.0);
            disciplinaService.criaDisciplina("GEOMETRIA ANALÍTICA E VETORES II",  "Vetores. Retas. Planos. Distâncias.",40.0);
            disciplinaService.criaDisciplina("LÓGICA PARA COMPUTAÇÃO II", "Aplicações de Portas Lógicas. Postulados da Álgebra de Boole e Teorema de De Morgan. Demonstração de Postulados Mediante Tabela-Verdade. Minimização de Expressões Booleanas: Método Algébrico e o Método do Mapa de Karnaugh. Circuitos Combinacionais: codificadores, decodificadores, multiplexador, demultiplexador, circuito somador. Sinal Digital. Circuitos Integrados: Família TTL e C-MOS.",40.0);
            disciplinaService.criaDisciplina("ALGORITMOS E ESTRUTURAS DE DADOS III", "Listas Lineares. Pilhas. Filas. Métodos de Busca e Ordenação.", 80.0);
            disciplinaService.criaDisciplina("ARQUITETURA E ORGANIZAÇÃO DE COMPUTADORES I", "Evolução e classificação de arquiteturas de computadores. Arquitetura de von Neumann. Estrutura básica de um computador. Unidades de Controle e de Processamento. Dispositivos de entrada e saída. Sistemas de interconexão (barramentos). Interfaces e técnicas de entrada e saída. Memória e representação de dados e de instruções. Hierarquia de memória. Mecanismo de acesso direto à memória. Arquiteturas RISC e CISC.",40.0);
            disciplinaService.criaDisciplina("CÁLCULO DIFERENCIAL E INTEGRAL III", "Funções de uma variável real a valores em Rn. Funções de várias variáveis reais. Limites de funções de várias variáveis. Derivadas parciais. Diferenciabilidade. Derivadas direcionais e gradiente. Extremos de funções de duas variáveis. Integral dupla. Integral tripla.",80.0);
            disciplinaService.criaDisciplina("ELETRÔNICA I", "Conceitos básicos de eletrodinâmica: corrente continua e alternada, DDP, resistência(Lei de Ohm), Geradores, Potência. Conceitos importantes sobre estrutura da matéria: átomos; elétrons, prótons e nêutrons. Definição de Capacitores, Impedância e conceituação e identificação desses componentes (código de cores). Associação de resistores e capacitores: série e paralelo; Noções importantes de Eletromagnetismo: Indutores, Transformadores e Relês.",40.0);
            disciplinaService.criaDisciplina("METODOLOGIA CIENTÍFICA", "Origem do pensamente científico. Características gerais do trabalho, do método e da pesquisa científicos. Técnicas de elaboração de pesquisa científica. Estrutura de Artigos e Monografia: documentação, projeto de pesquisa, relatório e informe científicos.",40.0);
            disciplinaService.criaDisciplina("PROBABILIDADE E ESTATÍSTICA I", "Estatística Descritiva. Análise Combinatória. Probabilidade.",40.0);
            disciplinaService.criaDisciplina("PROCESSAMENTO DIGITAL DE IMAGEM I", " História, Princípios básicos de Processamento de Imagens. Visão humana: pré-requisitos, desempenho. Visão artificial: mecanismos e arquitetura básica.Etapas básicas de Processamento de Imagens. Realce de Imagens.Operações matemáticas com imagens. Técnicas ponto a ponto. Histograma,equalização. Técnicas de vizinhança: Filtragem linear: Fourier, Convolução,espaciais. Fundamentos da Imagem Digital, Segmentação de Imagens.",40.0);
            disciplinaService.criaDisciplina("PROGRAMAÇÃO ORIENTADA À OBJETOS I", "Classes, Objetos, Métodos, Atributos. Variáveis de Instância. Utilização deArquivos. Métodos Construtores. Alocação Dinâmica de Objetos.",80.0);
            disciplinaService.criaDisciplina("TEORIA DA COMPUTAÇÃO I", "Introdução à Teoria das Linguagens Formais. Autômatos Finitos. Expressões Regulares. A Hierarquia de Chomsky. Linguagens Regulares, Livres de Contexto, Sensíveis ao Contexto e Com Estrutura de Frase. Autômatos de Pilha.", 40.0);
            disciplinaService.criaDisciplina("ÁLGEBRA LINEAR", "Sistemas Lineares e Matrizes. Espaços Vetoriais. Base e Dimensão. Transformações Lineares. Diagonalização de Operadores Lineares.",80.0);
            disciplinaService.criaDisciplina("ALGORITMOS E ESTRUTURAS DE DADOS IV", "Árvores. Grafos.", 80.0);
            disciplinaService.criaDisciplina("ARQUITETURA E ORGANIZAÇÃO DE COMPUTADORES II", "Processador, ciclo de instrução, formatos, endereçamento. Paralelismo no nível de instrução. Funcionamento básico de um pipeline. Arquiteturas paralelas. Multiprocessadores versus multicomputadores.", 40.0);
            disciplinaService.criaDisciplina("ELETRÔNICA II", "Instrumentos de Medição; Circuitos elétricos e Semicondutores. Programação e Utilização Básica de Microcontroladores.",40.0);
            disciplinaService.criaDisciplina("PROBABILIDADE E ESTATÍSTICA II", "Variáveis Aleatórias Discretas: Conceitos propriedades, distribuição de Bernoulli, Binomial e Poisson.Variáveis Aleatórias Continuas: Função de distribuição, Modelo uniforme, Normal e Exponencial.",40.0);
            disciplinaService.criaDisciplina("PROCESSAMENTO DIGITAL DE IMAGEM II", "Morfologia matemática. Codificação e compressão de imagem. Morphing, warping. Realçamento no domínio de frequência.",40.0);
            disciplinaService.criaDisciplina("PROGRAMAÇÃO ORIENTADA À OBJETOS II", "Herança Simples e múltipla. Polimorfismo. Classes, métodos e atributosestáticos. Interface. Objetos criados dinamicamente.",80.0);
            disciplinaService.criaDisciplina("SEGURANÇA DA INFORMAÇÃO", "Requisitos de segurança de aplicações, de base de dados e de comunicações. Segurança de dispositivos móveis. Políticas de segurança. Criptografia. Firewalls. Vulnerabilidades e principais tecnologias de segurança.", 40.0);
            disciplinaService.criaDisciplina("TEORIA DA COMPUTAÇÃO II", "Gramáticas. Máquinas de Turing. Classes de Problemas P e NP.",40.0);
            disciplinaService.criaDisciplina("BANCO DE DADOS I", "Introdução: evolução histórica dos sistemas de informação, conceitos básicos de sistemas de processamento de arquivos e de um SGBD (Sistema Gerenciador de Banco de Dados). Estrutura de um SGBD: níveis conceituais, externo e físico, modelos conceituais e físico. O modelo relacional: conceitos e prática com estudos de casos. Linguagem SQL (Structured Query Language), Linguagem SQL de Definição de Dados e Linguagem SQL de Manipulação de Dados.",80.0);
            disciplinaService.criaDisciplina("CÁLCULO NUMÉRICO","Erros de computador. Zeros de Funções Reais. Sistemas Lineares e sua resolução. Inversão de matrizes. Interpolação Numérica. Integração Numérica. Software matemático.", 80.0);
            disciplinaService.criaDisciplina("COMPILADORES I", "Introdução aos Compiladores e Interpretadores. Análise Léxica. Análise Sintática. Análise Semântica.", 40.0);
            disciplinaService.criaDisciplina("DESENVOLVIMENTO WEB I", "Linguagem de programação para web C# ASP.NET ou PHP (variáveis, controles de fluxo, orientação a objetos). Padrões de projeto. Integração com banco de dados.",80.0);
            disciplinaService.criaDisciplina("ENGENHARIA DE SOFTWARE I", "Introdução a Engenharia de Software, Ciclos de Vida de Software, Qualidade de Software, Qualidade do Processo de Software.", 40.0);
            disciplinaService.criaDisciplina("INTELIGÊNCIA ARTIFICIAL I", "Introdução aos conceitos gerais e históricos da Inteligência Artificial e agentes inteligentes. Conhecer sistemas especialistas. Compreensão e desenvolvimento por heurísticas. Emprego da lógica proposicional e de predicados. Formas de representação do conhecimento e inferência lógica. O tratamento da incerteza nas inferências. Compreensão e emprego da Lógica difusa (Fuzzy). A melhoria na qualidade dos dados por meio do processo de ETL (Extract Transform and Load). Introdução ao emprego de técnicas simples de classificação de dados (Machine Learning).",40.0);
            disciplinaService.criaDisciplina("LINGUAGEM E TÉCNICAS DE PROGRAMAÇÃO I", " Fundamentos e Conceitos deOrientação à Objetos. Fundamentos de Programação Orientada a Objetos em Java (variáveis e tipagem, constantes, operadores, controles de fluxo, vetores e matrizes, classes, packages, troca de mensagens, passagens de parâmetro, herança, polimorfismo, classes abstratas, interfaces, exceções), utilizando técnicas de programação e construção de programas com os pacotes disponíveis em Java, mostrando ao aluno o funcionamento de cada estrutura.",80.0);
            disciplinaService.criaDisciplina("SISTEMAS OPERACIONAIS I", "Introdução ao conceito e funcionamento dos Sistemas Operacionais, em seus aspectos de estrutura do Sistema Operacional, Concorrência, gerenciamento de processos, gerência da memória principal e memória virtual. Utilização dos sistemas operacionais mais conhecidos.", 40.0);
            disciplinaService.criaDisciplina("BANCO DE DADOS II", "Views, Stored Procedures, Functions, Triggers, Segurança e Restrições de Integridade. Álgebra relacional, índices e normalização.",80.0);
            disciplinaService.criaDisciplina("COMPILADORES II", "Verificação de Tipos. Geração de código intermediário. Otimização de Código.", 40.0);
            disciplinaService.criaDisciplina("DESENVOLVIMENTO WEB II", "Linguagem de programação para web C# ASP.NET ou PHP, upload de arquivos, cookies, sessões, ajax. Web Services utilizando XML e JSON. Desenvolvimento de uma aplicação web completa.",80.0);
            disciplinaService.criaDisciplina("ENGENHARIA DE SOFTWARE II", "Qualidade do Processo de Software, Técnicas de Teste de Software, Manutenção de Software, Engenharia Reversa e Reengenharia.", 40.0);
            disciplinaService.criaDisciplina("INTELIGÊNCIA ARTIFICIAL II", "Redes neurais e sua relação como cérebro humano. Lógica Fuzzy e aplicações. Sistemas Imunológicos Artificiais.",40.0);
            disciplinaService.criaDisciplina("LINGUAGEM E TÉCNICAS DE PROGRAMAÇÃO II", "Utilizar e integrar Banco de Dados com os conceitos de Orientação a objetos em Java. Programação utilizando Pacotes de Orientação Objetos para comunicação, utilizando técnicas de programação.",80.0);
            disciplinaService.criaDisciplina("OTIMIZAÇÃO LINEAR CONTÍNUA", "Programação Linear, Formulação, Solução Gráfica e o Método Simplex. O Dual do Problema de Programação Linear. Análise de sensibilidade. Aplicações.", 80.0);
            disciplinaService.criaDisciplina("SISTEMAS OPERACIONAIS II", "Sistemas Operacionais Multimídia. Sistemas Operacionais com Múltiplos Processadores. Segurança. Estudo de caso Operacional.: Linux. Prática em Projeto de Sistemas.",40.0);
            disciplinaService.criaDisciplina("ANÁLISE E PROJETOS DE SISTEMAS I", "Processo de Desenvolvimento De software, Modelagem, Documentação do Sistema, Conceitos de UML.",80.0);
            disciplinaService.criaDisciplina("COMPLEXIDADE DE ALGORITMOS I", "Medidas de Complexidade, Análise Assintótica de Limites de Complexidade. Notação \"O\", \"Ômega\" e \"Theta\". Medidas Empíricas de Performance. Relações de Recorrência para Análise de Algoritmos Recursivos. Análise de Algoritmos Iterativos e Recursivos.", 40.0);
            disciplinaService.criaDisciplina("COMPUTAÇÃO GRÁFICA I", "Origem e Objetivos da Computação gráfica. Dispositivos de entrada e saída.Representação Vetorial e Matricial. Conceitos básicos em computação gráficainterativa. Desenho de primitivas. Transformações geométricas em 2D e 3D.Composição de transformações. Transformação Viewport.",40.0);
            disciplinaService.criaDisciplina("DESENVOLVIMENTO DE APLICAÇÕES MOVEIS I", " Introdução ao Desenvolvimento Mobile. Noções de aplicações Móveis nativas e híbridas. Linguagem de programação para dispositivos móveis. Uso de recursos nativos dos dispositivos. Uso de Banco de Dados e Web Services.",40.0);
            disciplinaService.criaDisciplina("DESENVOLVIMENTO DE JOGOS I", " Histórico do desenvolvimento de Jogos, Design e Interface, Etapas de Produção, Jogos e Sociedade, Sistemas de Jogo.",40.0);
            disciplinaService.criaDisciplina("EMPREENDEDORISMO E INOVAÇÃO", "O mundo dos negócios. Abrindo um negócio na área de TI. Iniciando um plano de negócios (Modelo Canvas).",40.0);
            disciplinaService.criaDisciplina("PROGRAMAÇÃO PARALELA I", "Infraestrutura da Programação Paralela, Aplicações Distribuídas, Modelos de Concorrência, Programa concorrente.",40.0);
            disciplinaService.criaDisciplina("PROJETO DE GRADUAÇÃO I", "Dar suporte à execução da 1ª Etapa do Trabalho de Conclusão de Curso I. Planejamento e Desenvolvimento da Pesquisa Científica: as Etapas da Pesquisa, Pesquisa Bibliográfica, Experimental, Correlacional e de Campo. Metodologia de Observação e Registro do Comportamento. Técnicas de Pesquisa Bibliográfica: Itens Básicos para a Elaboração e Apresentação de Trabalho Científico. Normalização de Referência Bibliográfica.",40.0);
            disciplinaService.criaDisciplina("REDES E SISTEMAS DISTRIBUÍDOS I", "Conceitos básicos, protocolos, cabeamentos de rede, redes sem fio, equipamentos de rede, arquitetura de redes locais, segurança e desempenho de redes e projeto de redes locais.",80.0);
            disciplinaService.criaDisciplina("ROBÓTICA E AUTOMAÇÃO I", "Princípios e estratégias da automação, Automação e tecnologias de controle, Sistemas de controle industrial, Componentes de hardware para automação e controle de processos, Robótica industrial.", 40.0);
            disciplinaService.criaDisciplina("TRABALHO DE CONCLUSÃO DE CURSO I", "Não informada",100.0);
            disciplinaService.criaDisciplina("ANÁLISE E PROJETOS DE SISTEMAS II", "Diagramas em UML, Mecanismos Gerais de Projetos, Padrões de Projeto, Projeto em Análise de Sistemas.",80.0);
            disciplinaService.criaDisciplina("COMPLEXIDADE DE ALGORITMOS II", "Técnicas de Projeto de Algoritmos: Algoritmos do tipo Força Bruta, Algoritmos Gulosos, Divisão e Conquista, Programação Dinâmica. Introdução a Heurísticas e Metaheurísticas.", 40.0);
            disciplinaService.criaDisciplina("COMPUTAÇÃO GRÁFICA II", "Sistemas de Cores. Recorte de primitivas e técnicas de antiserrilhado.Projeções paralela e perspectiva. Mudança entre sistemas de coordenadas. Preenchimento de Polígonos e círculos. Modelagem 2D e 3D.",40.0);
            disciplinaService.criaDisciplina("DESENVOLVIMENTO DE APLICAÇÕES MOVEIS II", "Padrões de projeto de softwares mobiles. Frameworks para desenvolvimento mobile. Ambientes de desenvolvimento. Construindo uma aplicação completa mobile.",40.0);
            disciplinaService.criaDisciplina("DESENVOLVIMENTO DE JOGOS II", "Sistemas de Jogo, Processo de Desenvolvimento, Marketing e Manutenção. Desenvolvimento Prático de Jogos e suas variáveis.",40.0);
            disciplinaService.criaDisciplina("DESENVOLVIMENTO DE NOVOS NEGÓCIOS", "Identificando oportunidades de negócios. Criando uma Empresa de TI. Modelando uma Startup com CANVAS.",40.0);
            disciplinaService.criaDisciplina("PROGRAMAÇÃO PARALELA II", "Modelos de Programação, Escalonamento, Memória Distribuída.",40.0);
            disciplinaService.criaDisciplina("PROJETO DE GRADUAÇÃO II", "Dar suporte à execução da 2ª Etapa do Trabalho de Conclusão de Curso II. Desenvolvimento do projeto de pesquisa. Execução de normas. Confecção de Artigo baseado na Monografia. Revisão e correção ortográfica. Elaboração e Dissertação dos Resultados. Elaboração e Apresentação de Trabalho Científico.",40.0);
            disciplinaService.criaDisciplina("REDES E SISTEMAS DISTRIBUÍDOS II", "Modelo TCP/IP. Camada de Aplicação. Camada de Transporte. Camada de Enlace de Dados. Redes sem fio e Redes Móveis. Redes multimídia. Gerenciamento e Segurança em Redes de Computadores. Computação em Nuvem; SaaS; tecnologia on premises. Sistemas distribuídos.",80.0);
            disciplinaService.criaDisciplina("ROBÓTICA E AUTOMAÇÃO II", "Identificação automática e captura de dados, Linhas de produção automatizadas, Sistemas de montagem automatizados, Programas de qualidade para manufatura, Princípios e práticas de inspeção.",40.0);
            disciplinaService.criaDisciplina("TRABALHO DE CONCLUSÃO DE CURSO II", "Não informada", 100.0);
        }
    }

    private void criaCurso() {
        Curso curso = cursoService.getCursoByDescricao("Ciência da Computação");
        if (curso == null) {
            cursoService.criaCurso("Ciência da Computação");
        }
    }

    private void criaAluno() {
        if (alunoService.buscaAluno(Sessao.getId()) == null) {
            alunoService.criaAluno(Sessao.getId(), "Usuario");
        }
    }

    private void criaUsuario() {
        Usuario usuario = tableUsuario.checkUser("user", "user");
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setUsuario("user");
            usuario.setSenha("user");
            long id = tableUsuario.save(usuario);
            usuario.setId((int) id);
        }

        Sessao.setUser(usuario.getId(), usuario.getUsuario(), usuario.getSenha());
    }

    private void getGenericDatabase() {

        try {
            try (GenericDatabase<Aluno> tableAluno = new GenericDatabase<>(this, Aluno.class)) {
                tableAluno.getWritableDatabase();
            }

            try (GenericDatabase<Curso> tableCurso = new GenericDatabase<>(this, Curso.class)) {
                tableCurso.getWritableDatabase();
            }

            try (GenericDatabase<Disciplina> tableDisciplina = new GenericDatabase<>(this, Disciplina.class)) {
                tableDisciplina.getWritableDatabase();
            }

            try (GenericDatabase<Docente> tableDocente = new GenericDatabase<>(this, Docente.class)) {
                tableDocente.getWritableDatabase();
            }

            try (GenericDatabase<Termo> tableTermo = new GenericDatabase<>(this, Termo.class)) {
                tableTermo.getWritableDatabase();
            }

            try (GenericDatabase<AlunoDisciplinaTermo> tableAlunoDisciplinaTermo = new GenericDatabase<>(this, AlunoDisciplinaTermo.class)) {
                tableAlunoDisciplinaTermo.getWritableDatabase();
            }

            try (GenericDatabase<CursoDisciplina> tableCursoDisciplina = new GenericDatabase<>(this, CursoDisciplina.class)) {
                tableCursoDisciplina.getWritableDatabase();
            }

            try (GenericDatabase<Grade> tableCursoDisciplinaTermo = new GenericDatabase<>(this, Grade.class)) {
                tableCursoDisciplinaTermo.getWritableDatabase();
            }

            try (GenericDatabase<DisciplinaTermoProva> tableDisciplinaTermoProva = new GenericDatabase<>(this, DisciplinaTermoProva.class)) {
                tableDisciplinaTermoProva.getWritableDatabase();
            }

            try (GenericDatabase<DocenteDisciplinaTermo> tableDocenteDisciplinaTermo = new GenericDatabase<>(this, DocenteDisciplinaTermo.class)) {
                tableDocenteDisciplinaTermo.getWritableDatabase();
            }

            try (GenericDatabase<AlunoDisciplinaNota> tableAlunoDisciplinaNota = new GenericDatabase<>(this, AlunoDisciplinaNota.class)) {
                tableAlunoDisciplinaNota.getWritableDatabase();
            }

            tableUsuario = new GenericDatabase<>(this, Usuario.class);
            tableUsuario.getWritableDatabase();


        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public void onClickNotas(View view) {
        Intent intent = new Intent(this, Notas.class);
        startActivity(intent);
    }
}