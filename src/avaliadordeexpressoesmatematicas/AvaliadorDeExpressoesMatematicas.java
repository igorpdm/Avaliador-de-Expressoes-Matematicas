
package avaliadordeexpressoesmatematicas;

public class AvaliadorDeExpressoesMatematicas {


    public static void main(String[] args) throws Exception {
        Expressao expressao = new Expressao("1-2/3^4+5");
        System.out.println("Expressao Infixa: " + expressao);
        System.out.println("Expressao PosFixa: " + expressao.convertePosfixa());
        System.out.println("Valor da expressao: "+ expressao.calculaValor());
        
    }
    
}
