package avaliadordeexpressoesmatematicas;

import java.util.Scanner;

public class AvaliadorDeExpressoesMatematicas {

    public static void main(String[] args) throws Exception {
        Expressao[] lista = new Expressao[1000];
        MenuP(lista);
       // Expressao n1 = new Expressao("1-2/3^4+5");
       // System.out.println("Expressao infixa:\n" + n1.toStringI());
       // System.out.println(n1.verificaValidade());
       // n1.convertePosfixa();
       // System.out.println("Expressao PosFixa:\n" + n1.toStringP());
       // System.out.println("Valor da expressao: " + n1.calculaValor());

    }

    public static void MenuP(Expressao[] lista) throws Exception {
        Scanner exp = new Scanner(System.in);
        System.out.println("---------- Sistema Avalizador de Expressoes Matematicas ----------");
        System.out.println("1 - Inserir uma expressao no formato infixa.");
        System.out.println("2 - Manipular expressões inseridas.");
        System.out.println("3 - Sair do programa.");
        System.out.println("Selecione uma opcao: ");

        int op = exp.nextInt();

        switch (op) {
            case 1:
                Expressao temp = MenuE(lista);
                if (temp == null) {
                    MenuP(lista);
                } else {
                    MenuE1(temp, lista);
                }
                MenuP(lista);
            case 2:
                int i = 0;
                for (; lista[i]!= null; i++){}
                if (i == 0) {
                    System.out.println("Nenhuma expressão inserida.");
                    MenuP(lista);
                }
                System.out.println("Número de Expressões: "+i+"\nLista de Expressões armazenadas: ");
                int j;
                for (j = 0; j < i; j++) {
                    System.out.println(j+1+"\t\t"+lista[j].toStringI());
                }
                System.out.println("\nDigite o número relacionado a expressão que deseja alterar: ");
                int op1 = exp.nextInt();
                
                if (op1 > lista.length+1 || lista[op1] == null) {
                    System.out.println("Id inválido.");
                    System.out.println("Retornando...\n");
                    MenuP(lista);
                }
                MenuE1(lista[j-1], lista);
            case 3:
                System.exit(0);
        }

    }

    public static Expressao MenuE(Expressao[] lista) throws Exception {
        Scanner scn = new Scanner(System.in);

        System.out.print("Insira a expressao infixa: ");
        String exp = scn.nextLine();

        System.out.println("");
        if (exp.compareTo("") == 0) {
            System.out.println("Expressao sem conteudo.");
            System.out.println("Retornando...");
            System.out.println("");
            return null;
        } else {
            Expressao novo1 = new Expressao(exp);
            System.out.println("Validando expressão...");
            if (novo1.verificaValidade()) {
                System.out.println("Expressão Validada.");
                System.out.println("");
                for (int i = 0; i < lista.length; i++) {
                    if (lista[i] == null) {
                        lista[i] = novo1;
                        break;
                    }
                }
                return novo1;
            } else {
                System.out.println("Expressão informada não é válida. Deseja manter sem validação (Sim) ou (Nao)?");
                String op = scn.nextLine();
                if (op.compareTo("Sim") == 0) {
                    for (int i = 0; i < lista.length; i++) {
                        if (lista[i] == null) {
                            lista[i] = novo1;
                            return novo1;
                        }
                    }
                    System.out.println("Retornando...\t");
                    return null;
                } else {
                    System.out.println("Retornando...\t");
                    return null;
                }
            }
        }

    }

    public static void MenuE1(Expressao x, Expressao[] lista) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("---------- Manipulacao da expressao ----------");
        System.out.println("1 - Cenverter a expressão infixa para posfixa.");
        System.out.println("2 - Calcular a expressão.");
        System.out.println("3 - Retornar ao Menu Principal.");
        System.out.println("Selecione uma opcao: ");

        int op = scn.nextInt();

        switch (op) {
            case 1:
                System.out.println("Convertendo expressão...\t");
                x.convertePosfixa();
                System.out.println("Expressão convertida");
                System.out.println("Expressão Posfixa: " + x.toStringP());
                System.out.println("");
                MenuE1(x, lista);
            case 2:
                if (x.getExpressaoPosfixa() == null) {
                    System.out.println("A expressão não foi convertida para Posfixa. Converta-a primeiro para calcular o valor.");
                    System.out.println("");
                    MenuE1(x, lista);
                }
                System.out.println("Calculando expressão...");
                System.out.println("O valor da expressão é: " + x.calculaValor());
                System.out.println("");
                MenuE1(x, lista);
            case 3:
                MenuP(lista);
        }
    }
}
