import java.util.Objects;

public class Main {

    // Constantes para os comandos
    private static final char CMD_MOVE_LEFT = 'h';
    private static final char CMD_MOVE_RIGHT = 'l';
    private static final char CMD_REPLACE = 'r';

    private static class Result {
        private final String text;
        private final int cursor;

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
        public int hashCode() {
            return Objects.hash(text, cursor);
        }

        @Override
        public String toString() {
            return text + " - cursor: " + cursor;
        }
    }

    /**
     * Processa os comandos de manipulação de texto.
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
                case CMD_MOVE_LEFT:
                    cursor = moveCursorLeft(cursor, count);
                    break;
                case CMD_MOVE_RIGHT:
                    cursor = moveCursorRight(cursor, count, text.length());
                    break;
                case CMD_REPLACE:
                    if (i >= commands.length()) break;
                    char replacement = commands.charAt(i);
                    i++;
                    text = replaceCharacters(text, cursor, count, replacement);
                    // Reposiciona o cursor no último caractere modificado
                    cursor = Math.min(cursor + count, text.length()) - 1;
                    break;
                default:
                    System.out.println("Comando não reconhecido: " + command);
                    break;
            }
        }

        return new Result(text.toString(), cursor);
    }

    /**
     * Move o cursor para a esquerda.
     */
    private static int moveCursorLeft(int cursor, int count) {
        return Math.max(cursor - count, 0);
    }

    /**
     * Move o cursor para a direita.
     */
    private static int moveCursorRight(int cursor, int count, int textLength) {
        return Math.min(cursor + count, textLength - 1);
    }

    /**
     * Substitui os caracteres a partir da posição do cursor.
     */
    private static StringBuilder replaceCharacters(StringBuilder text, int cursor, int count, char replacement) {
        int end = Math.min(cursor + count, text.length());
        for (int j = cursor; j < end; j++) {
            text.setCharAt(j, replacement);
        }
        return text;
    }

    /**
     * Método de teste customizado.
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

        String inputText = "Hello Grupo Boticario";
        test(inputText, "hhlllllhlhhl", inputText, 4);
        test(inputText, "rhllllllrgllllllrb", "hello grupo boticario", 12);
        test(inputText, "rh6lrg6lrb2h", "hello grupo boticario", 10);
        test(inputText, "9999lrO", "Hello Grupo BoticariO", 20);
        test(inputText, "21rA", "AAAAAAAAAAAAAAAAAAAAA", 20);

        System.out.println("Todos os testes passaram.");
    }
}
