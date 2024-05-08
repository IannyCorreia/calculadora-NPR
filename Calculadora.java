package calculadora;
import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conversoes conversor = new Conversoes();

        System.out.println("Digite a expressão:");
        String expressao = scanner.nextLine();

        System.out.println("Digite o tipo de notação (infixa, prefixa ou posfixa):");
        String tipoNotacao = scanner.nextLine().toLowerCase();

        switch (tipoNotacao) {
            case "infixa":
                String posfixa = conversor.converterinfixaParaPosfixa(expressao);
                String prefixa = conversor.converterinfixaParaPrefixa(expressao);
                System.out.println("Expressão posfixa: " + posfixa);
                System.out.println("Expressão prefixa: " + prefixa);
                System.out.println("Resultado: " + Operacoes.calcularPosFixa(posfixa));
                break;
            case "prefixa":
                String expressaoPosfixa = conversor.converterprefixaParaPosfixa(expressao);
                String expressaoInfixa = conversor.converterprefixaParaInfixa(expressao);
                System.out.println("Expressão posfixa: " + expressaoPosfixa);
                System.out.println("Expressão infixa: " + expressaoInfixa);
                System.out.println("Resultado: " + Operacoes.calcularPosFixa(expressaoPosfixa));
                break;
            case "posfixa":
                expressaoInfixa = conversor.converterPosFixaParaInfixa(expressao);
                String expressaoPrefixa = conversor.converterposfixaParaPrefixa(expressao);
                System.out.println("Expressão infixa: " + expressaoInfixa);
                System.out.println("Expressão prefixa: " + expressaoPrefixa);
                System.out.println("Resultado: " + Operacoes.calcularPosFixa(expressao));
                break;
            default:
                System.out.println("Tipo de notação inválido.");
                break;
        }
        scanner.close();
    }
}
