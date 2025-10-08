# 🏥 Trabalho Prático – Sistema de Gerenciamento Hospitalar  

### 🎯 Objetivo  
Implementar um *Sistema de Gerenciamento Hospitalar* em *Java, aplicando conceitos avançados de **Programação Orientada a Objetos (POO), com foco em **herança, polimorfismo, encapsulamento, persistência de dados* e *regras de negócio mais complexas*.  

---
## Descrição do Projeto

Desenvolvimento de um sistema de gerenciamento hospitalar utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

## Dados do Aluno

- **Nome completo:** Victor Alexim Amaral
- **Matrícula:** 242005060
- **Curso:** Engenharias
- **Turma:** Turma 02

---

## Instruções para Compilação e Execução

1. **Compilação:**  
   Pelo vscode:
      Instale, na aba de extensões, a extensão "Extension Pack for Java"
      Compile diretamente pelo vscode

   Pelo terminal:
      Abra a pasta raiz
      Crie a pasta bin (caso não exista) com:
         mkdir bin
      Compile com
         javac -d bin -sourcepath src src\App.java


2. **Execução:**  
   Pelo vscode:
      Abra o arquivo App.java, no vscode
      Clique no botão de execução, no canto superior direito

   Pelo terminal:
      Execute com:
         java -cp bin App

3. **Estrutura de Pastas:**  
```
 App 
├── src/
│   │
│   ├── Entities/
│   │   ├── Paciente.java
│   │   ├── PacienteEspecial.java
│   │   ├── Medico.java
│   │   ├── Consulta.java
│   │   ├── Internacao.java
│   │   ├── PlanoSaude.java
│   │   ├── Especialidade.java
│   │   ├── DescontoEspecialidade.java
│   │   ├── Quarto.java
│   │   └── StatusConsulta.java
│   │
│   ├── Menus/
│   │   ├── App.java (Ponto de Entrada Principal)
│   │   ├── MenuPrincipal.java 
│   │   ├── MenuPacientes.java
│   │   ├── MenuMedicos.java
│   │   ├── MenuConsultas.java
│   │   ├── MenuInternacoes.java
│   │   ├── MenuCadastros.java
│   │   └── MenuRelatorios.java
│   │
│   ├── Persistencia/
│   │   ├── PacientePersistencia.java
│   │   ├── MedicoPersistencia.java
│   │   ├── ConsultaPersistencia.java
│   │   ├── InternacaoPersistencia.java
│   │   ├── PlanoSaudePersistencia.java
│   │   ├── EspecialidadePersistencia.java
│   │   └── QuartoPersistencia.java
│   │
│   └── Services/
│       │
│       ├── Nucleo/
│       │   ├── PacienteService.java
│       │   ├── MedicoService.java
│       │   ├── ConsultaService.java
│       │   ├── InternacaoService.java
│       │   └── CadastroService.java
│       │
│       ├── Relatorios/
│       │   ├── RelatorioPacientes.java
│       │   ├── RelatorioMedicos.java
│       │   ├── RelatorioConsultas.java
│       │   ├── RelatorioInternacoes.java
│       │   └── RelatorioEstatisticas.java
│       │
│       ├── HospitalService.java (Fachada Principal)
│       └── RelatorioService.java (Fachada de Relatórios)
│ 
└── README.md
```

3. **Versão do JAVA utilizada:**  
   javac 21.0.8

---

## Vídeo de Demonstração

[Video no Drive](https://drive.google.com/file/d/1tReBE4lgGKUPIYl0svAe5IRqCD9AYhBW/view?usp=sharing)

---

## Prints da Execução

1. Menu Principal:  
   ![Menu Principal](App/Prints/image.png)

2. Cadastro de Médico:  
   ![Cadastro Medico](App/Prints/image-2.png)

3. Relatório de Internações:  
   ![Relatorio Internações](App/Prints/image-3.png)

---

## Observações (Extras ou Dificuldades)

- Apanhei bastante até conseguir usar o LocalDate, mas gostei muito da funcionalidade, além de descobrir e aprender a listagem com ":" e o tipo de variável "Optional"

---

## Contato

- aleximamaral@gmail.com

---

### 🖥️ Descrição do Sistema  

O sistema deve simular o funcionamento de um hospital com cadastro de *pacientes, médicos, especialidades, consultas e internações*.  

1. *Cadastro de Pacientes*  
   - Pacientes comuns e pacientes especiais (ex: com plano de saúde).  
   - Cada paciente deve ter: nome, CPF, idade, histórico de consultas e internações.  

2. *Cadastro de Médicos*  
   - Médicos podem ter especialidades (ex: cardiologia, pediatria, ortopedia).  
   - Cada médico deve ter: nome, CRM, especialidade, custo da consulta e agenda de horários.  

3. *Agendamento de Consultas*  
   - Um paciente pode agendar uma consulta com um médico disponível.  
   - Consultas devem registrar: paciente, médico, data/hora, local, status (agendada, concluída, cancelada).  
   - Pacientes especiais (plano de saúde) podem ter *vantagens*, como desconto.  
   - Duas consultas não podem estar agendadas com o mesmo médico na mesma hora, ou no mesmo local e hora

4. *Consultas e Diagnósticos*  
   - Ao concluir uma consulta, o médico pode registrar *diagnóstico* e/ou *prescrição de medicamentos*.  
   - Cada consulta deve ser registrada no *histórico do paciente*.  

5. *Internações*  
   - Pacientes podem ser internados.  
   - Registrar: paciente, médico responsável, data de entrada, data de saída (se já liberado), quarto e custo da internação.  
   - Deve existir controle de *ocupação dos quartos* (não permitir duas internações no mesmo quarto simultaneamente).  
   - Internações devem poder ser canceladas, quando isso ocorrer, o sistema deve ser atualizado automaticamente.

6. *Planos de saúde*    
   -  Planos de saude podem ser cadastrados.
   -  Cada plano pode oferecer *descontos* para *especializações* diferentes, com possibilidade de descontos variados.
   -  Um paciente que tenha o plano de saúde deve ter o desconto aplicado.
   -  Deve existir a possibilidade de um plano *especial* que torna internação de menos de uma semana de duração gratuita.
   -  Pacientes com 60+ anos de idade devem ter descontos diferentes.

7. *Relatórios*  
   - Pacientes cadastrados (com histórico de consultas e internações).  
   - Médicos cadastrados (com agenda e número de consultas realizadas).  
   - Consultas futuras e passadas (com filtros por paciente, médico ou especialidade).  
   - Pacientes internados no momento (com tempo de internação).  
   - Estatísticas gerais (ex: médico que mais atendeu, especialidade mais procurada).  
   - Quantidade de pessoas em um determinado plano de saúde e quanto aquele plano *economizou* das pessoas que o usam.  


---

### ⚙️ Requisitos Técnicos  
- O sistema deve ser implementado em *Java*.  
- Interface via *terminal (linha de comando)*.  
- Os dados devem ser persistidos em *arquivos* (.txt ou .csv).  
- Deve existir *menu interativo*, permitindo navegar entre as opções principais.  

---

### 📊 Critérios de Avaliação  

1. *Modos da Aplicação (1,5)* → Cadastro de pacientes, médicos, planos de saúde, consultas e internações.  
2. *Armazenamento em arquivo (1,0)* → Dados persistidos corretamente, leitura e escrita funcional.  
3. *Herança (1,0)* → Ex.: Paciente e PacienteEspecial, Consulta e ConsultaEspecial, Médico e subclasses por especialidade.  
4. *Polimorfismo (1,0)* → Ex.: regras diferentes para agendamento, preços de consultas.
5. *Encapsulamento (1,0)* → Atributos privados, getters e setters adequados.  
6. *Modelagem (1,0)* → Estrutura de classes clara, bem planejada e com relacionamentos consistentes.  
7. *Execução (0,5)* → Sistema compila, roda sem erros e possui menus funcionais.  
8. *Qualidade do Código (1,0)* → Código limpo, organizado, nomes adequados e boas práticas.  
9. *Repositório (1,0)* → Uso adequado de versionamento, commits frequentes com mensagens claras.  
10. *README (1,0)* → Vídeo curto (máx. 5 min) demonstrando as funcionalidades + prints de execução + explicação da modelagem.  

🔹 *Total = 10 pontos*  
🔹 *Pontuação extra (até 1,5)* → Melhorias relevantes, como:  
- Sistema de triagem automática com fila de prioridade.  
- Estatísticas avançadas (tempo médio de internação, taxa de ocupação por especialidade).  
- Exportação de relatórios em formato .csv ou .pdf.  
- Implementação de testes unitários para classes principais.  
- Menu visual.
