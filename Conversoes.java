package calculadora;
import java.util.Stack;

public class Conversoes {
    public String converterinfixaParaPosfixa(String infixa) {
        StringBuilder posfixa = new StringBuilder();
        Stack<Character> pilha = new Stack<>();
    
        for (char operador: infixa.toCharArray()) {
            if (Character.isDigit(operador)) {
                posfixa.append(operador).append(' ');
            } else if (operador == '(') {
                pilha.push(operador);
            } else if (operador == ')') {
                while (!pilha.isEmpty() && pilha.peek() != '(') {
                    posfixa.append(pilha.pop()).append(' '); 
                }
                pilha.pop();
            } else if (verificarOperador(operador)) {
                while (!pilha.isEmpty() && prioridade(operador) <= prioridade(pilha.peek())) {
                    posfixa.append(pilha.pop()).append(' '); 
                }
                pilha.push(operador);
            }
        }
        while (!pilha.isEmpty()) {
            if (pilha.peek() == '(') {
                return "Expressão inválida";
            }
            posfixa.append(pilha.pop()); 
        }
        return posfixa.toString();
    }
    

    public String converterinfixaParaPrefixa(String infixa) {
        StringBuilder prefixa = new StringBuilder();
        Stack<Character> pilha = new Stack<>();
        StringBuilder infixaReversa = new StringBuilder(infixa).reverse();

        for (char caractere : infixaReversa.toString().toCharArray()) {
            if (Character.isLetterOrDigit(caractere)) {
                prefixa.insert(0, caractere);
            } else if (caractere == ')') {
                pilha.push(caractere);
            } else if (caractere == '(') {
                while (!pilha.isEmpty() && pilha.peek() != ')') {
                    prefixa.insert(0, pilha.pop());
                }
                pilha.pop();
            } else if (verificarOperador(caractere)) {
                while (!pilha.isEmpty() && prioridade(caractere) < prioridade(pilha.peek())) {
                    prefixa.insert(0, pilha.pop());
                }
                pilha.push(caractere);
            }
        }
        while (!pilha.isEmpty()) {
            prefixa.insert(0, pilha.pop());
        }

        return prefixa.toString();
    }
    
    public String converterprefixaParaInfixa(String expressaoPrefixa) {
        Stack<String> pilha = new Stack<>();
        String[] elementos = expressaoPrefixa.split(" ");


        for (int i = elementos.length - 1; i >= 0; i--) {
            String elemento = elementos[i];

            if (verificarOperando(elemento)) {
                pilha.push(elemento);
            } else {
                String operando2 = pilha.pop();
                String operando1 = pilha.pop();
                String resultado = "(" + operando1 + elemento + operando2 + ")";
                pilha.push(resultado);
            }
        }

        return pilha.pop();
    }

    public String converterprefixaParaPosfixa(String expressao) {
        Stack<String> pilha = new Stack<>();
        String[] elementos = expressao.split(" ");

        for (int i = elementos.length - 1; i >= 0; i--) {
            String elemento = elementos[i];

            if (verificarOperando(elemento)) {
                pilha.push(elemento);
            } else {
                String operando2 = pilha.pop();
                String operando1 = pilha.pop();
                String resultado = operando2 + " " + operando1 + " " + elemento;
                pilha.push(resultado);
            }
        }

        return pilha.pop();
    }
    public String converterPosFixaParaInfixa(String expressao) {
        Stack<String> pilha = new Stack<>();

        for (char caracter : expressao.toCharArray()) {
            if (caracter == ' ') {
                continue;
            }

            if (Character.isDigit(caracter)) {
                pilha.push(String.valueOf(caracter));
            } else {
                String operando2 = pilha.pop();
                String operando1 = pilha.pop();
                String resultado = "(" + operando1 + caracter + operando2 + ")";
                pilha.push(resultado);
            }
        }

        return pilha.pop();
    }

        public String converterposfixaParaPrefixa(String expressaoPosfixa) {
        Stack<String> pilha = new Stack<>();
        String[] elementos = expressaoPosfixa.split(" ");

        for (String elemento : elementos) {
            if (verificarOperando(elemento)) {
                pilha.push(elemento);
            } else {
                String operando2 = pilha.pop();
                String operando1 = pilha.pop();
                String resultado = elemento + " " + operando1 + " " + operando2;
                pilha.push(resultado);
            }
        }

        return pilha.pop();
    }
    public static boolean verificarOperando(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false; 
            }
            else{
                return true;
            }
        }
        return true;
    }

    private static boolean verificarOperador(char operador) {
        return operador == '+' || operador == '-' || operador == '*' || operador == '/';
    }
    public int prioridade(char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        } else {
            return 0;
        }
    }
}
