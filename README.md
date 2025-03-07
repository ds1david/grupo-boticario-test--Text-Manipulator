# Text Manipulator

Este projeto implementa uma solução simples em Java para manipulação de texto com base em uma sequência de comandos. A solução utiliza um método de teste customizado para validar o comportamento do código conforme os exemplos propostos.

## Instruções do Teste

Crie um programa Java simples para manipulação de texto onde, ao receber um texto e uma sequência de comandos, é realizada uma modificação no texto e retornado um resultado contendo o texto alterado e a posição final do cursor.

Os comandos permitidos são:

- **h**: move o cursor para a esquerda.
- **l**: move o cursor para a direita.
- **r<char>**: substitui o caractere na posição atual pelo `<char>`.

Além disso, é possível prefixar os comandos com um número para indicar quantas vezes o comando deve ser executado. Assim, temos:

- **[N]h**: move o cursor N caracteres para a esquerda.
- **[N]l**: move o cursor N caracteres para a direita.
- **[N]r<char>**: substitui N caracteres consecutivos, a partir da posição atual, pelo `<char>` e reposiciona o cursor no último caractere alterado.

## Exemplos

```
Entrada de texto: Hello Grupo Boticario
Comandos: hhlllllhlhhl
Saída: Hello Grupo Boticario - cursor: 4

Entrada de texto: Hello Grupo Boticario
Comandos: rhllllllrgllllllrb
Saída: hello grupo boticario - cursor: 12

Entrada de texto: Hello Grupo Boticario
Comandos: rh6lrg6lrb2h
Saída: hello grupo boticario - cursor: 10

Entrada de texto: Hello Grupo Boticario
Comandos: 9999lrO
Saída: Hello Grupo BoticariO - cursor: 20

Entrada de texto: Hello Grupo Boticario
Comandos: 21rA
Saída: AAAAAAAAAAAAAAAAAAAAA - cursor: 20
```

## Funcionalidades

- **Movimentação do Cursor:**
    - **h:** Move o cursor para a esquerda.
    - **l:** Move o cursor para a direita.

  Quando precedidos por um número (ex.: `3h` ou `5l`), o cursor se move a quantidade especificada, sem ultrapassar os limites do texto.

- **Substituição de Caracteres:**
    - **r:** Substitui o caractere na posição atual pelo caractere informado imediatamente após o comando.
    - Quando precedido por um número (ex.: `4r`), substitui N caracteres a partir da posição atual e reposiciona o cursor para o último caractere modificado.

## Estrutura do Código

O código está implementado na classe `Main` e possui os seguintes componentes:

### 1. Constantes para os Comandos

Para melhorar a legibilidade e facilitar a manutenção do código, os comandos são definidos por constantes:

- `CMD_MOVE_LEFT` (valor `'h'`): Comando para mover o cursor para a esquerda.
- `CMD_MOVE_RIGHT` (valor `'l'`): Comando para mover o cursor para a direita.
- `CMD_REPLACE` (valor `'r'`): Comando para substituir caracteres.

### 2. Classe Interna `Result`

- **Descrição:**  
  Representa o resultado da manipulação de texto, contendo o texto final e a posição final do cursor.

- **Principais Métodos:**
    - **Construtor:** Inicializa os atributos `text` e `cursor`.
    - **equals:** Permite comparar dois objetos `Result` para ver se possuem o mesmo texto e posição do cursor.
    - **toString:** Retorna uma representação em string do resultado, facilitando sua visualização.

### 3. Método `process(String inputText, String commands)`

- **Descrição:**  
  Recebe o texto original e a sequência de comandos, realizando as alterações necessárias e retornando um objeto `Result` com o texto final e a posição final do cursor.

- **Funcionamento:**
    - **Acumulação de Dígitos:**  
      Antes de cada comando, o método acumula dígitos para determinar quantas vezes o comando será executado. Se nenhum dígito for encontrado, o valor padrão é 1.

    - **Processamento dos Comandos:**
        - **CMD_MOVE_LEFT (`h`):** Move o cursor para a esquerda sem permitir que ele fique fora dos limites do texto.
        - **CMD_MOVE_RIGHT (`l`):** Move o cursor para a direita, garantindo que não ultrapasse o fim do texto.
        - **CMD_REPLACE (`r`):**  
          Utiliza o caractere imediatamente após o comando como o caractere de substituição.  
          Se precedido por um número maior que 1, substitui N caracteres consecutivos a partir da posição atual e reposiciona o cursor no último caractere modificado.

- **Retorno:**  
  O método retorna um objeto da classe `Result` contendo o texto modificado e a posição final do cursor.

### 4. Método `test(String inputText, String commands, String expectedText, int expectedCursor)`

- **Descrição:**  
  Método customizado para testes, que compara o resultado obtido pelo método `process` com o resultado esperado.

- **Funcionamento:**
    - Executa o método `process` com os parâmetros fornecidos.
    - Compara o resultado obtido com o esperado.
    - Caso os resultados não coincidam, lança uma exceção com uma mensagem de erro; caso contrário, exibe uma mensagem confirmando o sucesso do teste.

### 5. Método `main(String[] args)`

- **Descrição:**  
  Ponto de entrada da aplicação.

- **Funcionamento:**
    - Executa uma série de testes utilizando o método `test` com os exemplos fornecidos.
    - Caso todos os testes passem, exibe a mensagem "Todos os testes passaram."

## Como Compilar e Executar

1. **Compilação:**  
   Utilize o comando abaixo para compilar o código:
   ```bash
   javac Main.java
   ```
2. **Execução:**  
   Após a compilação, execute a aplicação:
   ```bash
   java Main
   ```
   Se todos os testes forem bem-sucedidos, a saída exibirá mensagens indicando que cada teste passou, finalizando com:
   ```lua
   Todos os testes passaram.
   ```
