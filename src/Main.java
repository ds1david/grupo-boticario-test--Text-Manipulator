public class Main {

    // Classe auxiliar para armazenar o resultado: texto final e posição do cursor.
    public static class Result {
        public String text;
        public int cursor;

        public Result(String text, int cursor) {
            this.text = text;
            this.cursor = cursor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Result)) return false;
            Result that = (Result) o;
            return this.cursor == that.cursor && this.text.equals(that.text);
        }

        @Override
        public String toString() {
            return text + " - cursor: " + cursor;
        }
    }

    /**
     * Processa os comandos de manipulação de texto.
     *
     * Comandos:
     *  - h: move o cursor para a esquerda.
     *  - l: move o cursor para a direita.
     *  - r: substitui o(s) caractere(s) na posição atual pelo caractere que vem logo após o 'r'.
     *
     * Cada comando pode vir precedido de um número que indica a quantidade. Se não houver número, o valor padrão é 1.
     *
     * @param inputText Texto de entrada.
     * @param commands  Comandos para manipulação.
     * @return Objeto Result com o texto final e a posição final do cursor.
     */
    public static Result process(String inputText, String commands) {
        StringBuilder text = new StringBuilder(inputText);
        int cursor = 0;
        int i = 0;

        while (i < commands.length()) {
            int count = 0;
            // Acumula dígitos para definir a quantidade
            while (i < commands.length() && Character.isDigit(commands.charAt(i))) {
                count = count * 10 + (commands.charAt(i) - '0');
                i++;
            }
            if (count == 0) {
                count = 1;
            }

            if (i >= commands.length()) break;

            char command = commands.charAt(i);
            i++;

            switch (command) {
                case 'h':
                    // Move o cursor para a esquerda, sem sair dos limites
                    cursor = Math.max(cursor - count, 0);
                    break;
                case 'l':
                    // Move o cursor para a direita, sem exceder o tamanho do texto
                    cursor = Math.min(cursor + count, text.length() - 1);
                    break;
                case 'r':
                    // O próximo caractere é o de substituição
                    if (i >= commands.length()) break;
                    char replacement = commands.charAt(i);
                    i++;

                    if (count == 1) {
                        text.setCharAt(cursor, replacement);
                    } else {
                        int end = Math.min(cursor + count, text.length());
                        for (int j = cursor; j < end; j++) {
                            text.setCharAt(j, replacement);
                        }
                        // Após a substituição, reposiciona o cursor no último caractere modificado
                        cursor = end - 1;
                    }
                    break;
                default:
                    // Comando não reconhecido, ignora.
                    break;
            }
        }

        return new Result(text.toString(), cursor);
    }

    /**
     * Método de teste customizado.
     * Compara o resultado obtido com o resultado esperado.
     */
    public static void test(String inputText, String commands, String expectedText, int expectedCursor) {
        Result result = process(inputText, commands);
        Result expected = new Result(expectedText, expectedCursor);
        if (!result.equals(expected)) {
            throw new RuntimeException("Teste falhou para entrada: '" + inputText + "' com comandos: '" + commands +
                    "'. Resultado obtido: " + result + ", esperado: " + expected);
        } else {
            System.out.println("Teste passou para entrada: '" + inputText + "' com comandos: '" + commands +
                    "'. Resultado: " + result);
        }
    }

    public static void main(String[] args) {
        // Execução dos testes com os exemplos fornecidos

        // Teste 1:
        test("Hello Grupo Boticario", "hhlllllhlhhl", "Hello Grupo Boticario", 3);

        // Teste 2:
        test("Hello Grupo Boticario", "rhllllllrgllllllrb", "hello grupo boticario", 12);

        // Teste 3:
        test("Hello Grupo Boticario", "rh6lrg6lrb2h", "hello grupo boticario", 10);

        // Teste 4:
        test("Hello Grupo Boticario", "9999lrO", "Hello Grupo BoticariO", 20);

        // Teste 5:
        test("Hello Grupo Boticario", "21rA", "AAAAAAAAAAAAAAAAAAAAA", 20);

        System.out.println("Todos os testes passaram.");
    }
}
