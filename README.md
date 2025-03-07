# Text Manipulator

Este projeto implementa uma solução simples em Java para manipulação de texto com base em uma sequência de comandos. A solução utiliza um método de teste customizado para validar o comportamento do código conforme os exemplos propostos.

## Instruções do teste

Crie um java simples para manipulação de texto onde ao entrar com um texto com comandos, realizo alterações no texto e retorno um resultado.

Os comandos por caracteres são:

* h: para mover o cursos para a esqueda.
* l: para mover o cursor para a direita.
* r<char>: atualiza o caracter na posição atual pelo <char>.
* [N]h: move o cursor N caracteres para a esquerda.
* [N]l: move o cursor N caracteres para a direita.
* [N]r<char>: atualiza N caracteres, começando pela posição do cursor, pelo <char> e move o cursor de posição.

## Exemplos:
```
Entrada de texto: Hello Grupo Boticario
Comandos: hhlllllhihhi
Saída: Hello Grupo Boticario - cursor: 3

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

- **Movimentação do cursor:**
    - `h`: move o cursor para a esquerda.
    - `l`: move o cursor para a direita.
    - Quando precedidos por um número (ex.: `3h` ou `5l`), o cursor se move a quantidade especificada.

- **Substituição de caracteres:**
    - `r`: substitui o caractere na posição atual pelo caractere informado imediatamente após o comando.
    - Quando precedido por um número (ex.: `4r`), substitui _N_ caracteres a partir da posição atual, reposicionando o cursor no último caractere modificado.

## Estrutura do Código

O código está contido na classe `TextManipulator` e possui os seguintes componentes:

### 1. Classe Interna `Result`

- **Descrição:**  
  Representa o resultado da manipulação, contendo o texto final e a posição final do cursor.
- **Principais métodos:**
    - Construtor para inicialização dos atributos.
    - `equals`: para comparação dos resultados nos testes.
    - `toString`: para facilitar a visualização do resultado.

### 2. Método `process(String inputText, String commands)`

- **Descrição:**  
  Este método recebe o texto original e a sequência de comandos.  
  Ele utiliza um `StringBuilder` para manipular o texto de forma mutável e um cursor para indicar a posição atual no texto.

- **Funcionamento:**
    - **Acumulação de dígitos:**  
      Antes de cada comando, o método acumula dígitos para determinar a quantidade de vezes que o comando deve ser aplicado. Se nenhum dígito for encontrado, o valor padrão é 1.
    - **Processamento dos comandos:**
        - Para `h`: move o cursor para a esquerda (não permitindo que o cursor saia dos limites do texto).
        - Para `l`: move o cursor para a direita (limitando o movimento ao tamanho do texto).
        - Para `r`: utiliza o caractere seguinte na string de comandos como caractere de substituição. Se for precedido por um número maior que 1, substitui vários caracteres consecutivos e reposiciona o cursor no último caractere substituído.

- **Retorno:**  
  Um objeto da classe `Result` contendo o texto modificado e a posição final do cursor.

### 3. Método `test(String inputText, String commands, String expectedText, int expectedCursor)`

- **Descrição:**  
  Método customizado para testes que compara o resultado obtido do método `process` com o resultado esperado.
- **Funcionamento:**
    - Executa o método `process` com os parâmetros fornecidos.
    - Compara o resultado com o esperado.
    - Caso os resultados não coincidam, lança uma exceção com uma mensagem de erro. Se coincidir, exibe uma mensagem de sucesso.

### 4. Método `main(String[] args)`

- **Descrição:**  
  Ponto de entrada da aplicação.
- **Funcionamento:**
    - Executa os testes utilizando o método customizado `test` com os exemplos fornecidos.
    - Caso todos os testes passem, exibe a mensagem "Todos os testes passaram."

## Como Compilar e Executar

1. **Compilação:**  
   Utilize o comando abaixo para compilar o código:
   ```bash
   javac TextManipulator.java
2. **Execução:**
Após a compilação, execute a aplicação:
   ```bash
   java TextManipulator.java
Se todos os testes forem bem-sucedidos, a saída exibirá mensagens indicando que cada teste passou, finalizando com:
   ```lua
    Todos os testes passaram.