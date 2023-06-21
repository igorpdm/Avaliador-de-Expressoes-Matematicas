# Avaliador de Expressões Matemáticas

Este é um programa simples em Java que permite avaliar expressões matemáticas fornecidas pelo usuário. Ele converte a expressão da forma infixada para a forma posfixada (notação polonesa reversa) e, em seguida, calcula o valor da expressão.

## Como usar

1. Clone o repositório ou faça o download dos arquivos.
2. Abra o projeto em um ambiente de desenvolvimento Java.
3. Navegue até a classe `AvaliadorDeExpressoesMatematicas` e execute o método `main`.
4. Modifique a expressão dentro do método `main` de acordo com sua necessidade. Por exemplo, você pode alterar a linha `Expressao expressao = new Expressao("1-2/3^4+5");` para avaliar uma expressão diferente.
5. Execute o programa e veja os resultados no console.

## Exemplo

Aqui está um exemplo de como usar o programa:

```java
public class AvaliadorDeExpressoesMatematicas {

    public static void main(String[] args) throws Exception {
        Expressao expressao = new Expressao("1-2/3^4+5");
        System.out.println("Expressao Infixa: " + expressao);
        System.out.println("Expressao PosFixa: " + expressao.convertePosfixa());
        System.out.println("Valor da expressao: " + expressao.calculaValor());   
    }
    
}
```
