
package avaliadordeexpressoesmatematicas;

public class AvaliadorDeExpressoesMatematicas {


    public static void main(String[] args) throws Exception {
        Expressao expressao = new Expressao("1-2/3^4+5");
        System.out.println("Expressao Ifixa:\n" + expressao);
        System.out.println("Expressao PosFixa:\n" + expressao.convertePosfixa());

        
    }
    
}
