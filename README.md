# Dev Journey

Este projeto é um jogo de simulação de carreira em console (CLI) desenvolvido em Java, onde o jogador avança de Estagiário a CEO, gerenciando projetos e habilidades.

##  Informações de Entrega

* **Membros da Equipe:** `Ian de Sousa Pereira, 00000850687`
---
## Como Executar o Jogo:

* Java Development Kit (JDK) 8 ou superior.

### Passos para Execução

1.  **Clone o Repositório:**
    ```bash
    git clone [COLE A URL DO SEU REPOSITÓRIO AQUI]
    ```
2.  **Compile o Código:** (A partir do diretório raiz do projeto)
    ```bash
    # Compila todos os arquivos em seus respectivos pacotes
    javac game/*.java game/challenges/*.java game/projects/*.java game/service/*.java game/exceptions/*.java
    ```
3.  **Execute o Jogo:**
    ```bash
    java game.Main
    ```
    *O jogo iniciará solicitando o nome do Dev.*

---

## Funcionamento do Jogo

O jogo é controlado pelo menu principal e opera em um sistema de turnos.

### 1. Progressão de Carreira

| Cargo | Requisito (XP) | Observações |
| :--- | :--- | :--- |
| **Estagiário** | 0 | Início do jogo (50 XP inicial). |
| **Júnior** | 2000 | |
| **Pleno** | 4000 | |
| **Sênior** | 8000 | |
| **CEO** | 15000 | Meta Final. |

* **Promoção:** A opção **"Tentar Promoção"** verifica se o XP acumulado atinge a meta do próximo nível. Em caso de sucesso, o XP é **zerado**.

### 2. Sistema de Projetos por Turnos

O trabalho em projetos é incremental:

* **Progresso:** Cada turno de trabalho aumenta o progresso do projeto ativo em **10%** e concede **5 XP** de esforço.
* **Conclusão:** A recompensa total de XP é concedida somente ao atingir 100%.
* **Reposição:** Novos lotes de projetos são gerados automaticamente quando a lista disponível se esgota.

### 3. Mecânica de Alto Risco e Recompensa

O **`GirlfriendChallenge`** introduz uma mecânica de *boost* permanente:

| Escolha | Efeito Imediato | Recompensa de Longo Prazo |
| :--- | :--- | :--- |
| **4. Terminar** | Perda de **50 XP**. | Ativa **Boost de XP de 4.0x** (300% de aumento) para os **próximos 5 projetos**. |

### 4. Desafios e Comandos

* **Desafios (`Challenges`):** Há **30% de chance** de um desafio surpresa (`HRChecking` ou `GirlfriendChallenge`) ser acionado após cada turno do menu, resultando em ganhos ou perdas de XP.
* **Easter Egg:** Digitar **`sudo`** no menu de opções concede um grande bônus imediato de XP.

---