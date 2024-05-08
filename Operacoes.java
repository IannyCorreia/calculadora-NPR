package calculadora;
import java.util.Stack;

public class Operacoes {

    public static double calcularPosFixa(String expressaoPosfixa) {
        Stack<Double> pilha = new Stack<>();
        String[] elementos = expressaoPosfixa.split(" "); //separamos os elementos entre espaços
    
        for (String elemento : elementos) { //é declarado elemento em elemnetos, cada elemento terá o valor de elementos só que com seus espaços removidos
            elemento = elemento.trim();
    
            if (!elemento.isEmpty()) { //se elemento nao estiver vazio 
                if (elemento.matches("\\d+(\\.\\d+)?")) {
                    pilha.push(Double.parseDouble(elemento));
                } else if (Character.isDigit(elemento.charAt(0))) {
                    pilha.push(Double.parseDouble(elemento));
                } else {
                    double operando2 = pilha.pop();
                    double operando1 = pilha.pop();
                    switch (elemento) {
                        case "+":
                            pilha.push(operando1 + operando2);
                            break;
                        case "-":
                            pilha.push(operando1 - operando2);
                            break;
                        case "*":
                            pilha.push(operando1 * operando2);
                            break;
                        case "/":
                            if (operando2 == 0) {
                                throw new ArithmeticException("Erro: divisão por 0");
                            }
                            pilha.push(operando1 / operando2);
                            break;
                        default:
                            throw new IllegalArgumentException("Operador inválido: " + elemento);
                    }
                }
            }
        }
        if (!pilha.isEmpty()) {
            return pilha.pop();
        } else {
            throw new IllegalArgumentException("Expressão inválida: a pilha está vazia");
        }
    }
    
}    