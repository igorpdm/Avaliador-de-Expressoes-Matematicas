package avaliadordeexpressoesmatematicas;

public class Expressao {

    private Character[] expressaoInfixa;
    private Character[] expressaoPosfixa;

    public Expressao(String exp) {

        this.expressaoInfixa = new Character[exp.length()];
        for (int i = 0; i < exp.length(); i++) {
            this.getExpressaoInfixa()[i] = exp.charAt(i);
        }

    }

    public int calculaValor() {
        try {
            Pilha<Integer> pilha = new Pilha<>(this.getExpressaoPosfixa().length);

            for (int i = 0; i < this.getExpressaoPosfixa().length; i++) {
                char c = this.getExpressaoPosfixa()[i];

                if (Character.isDigit(c)) {
                    pilha.push(Character.getNumericValue(c));
                } else {
                    int operando2 = pilha.pop();
                    int operando1 = pilha.pop();
                    int resultado;

                    switch (c) {
                        case '+':
                            resultado = operando1 + operando2;
                            break;
                        case '-':
                            resultado = operando1 - operando2;
                            break;
                        case '*':
                            resultado = operando1 * operando2;
                            break;
                        case '/':
                            resultado = operando1 / operando2;
                            break;
                        case '^':
                            resultado = (int) Math.pow(operando1, operando2);
                            break;
                        default:
                            throw new IllegalArgumentException("Operador inválido: " + c);
                    }

                    pilha.push(resultado);
                }
            }

            return pilha.pop();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void convertePosfixa() throws Exception {
        String posfixa = "";
        Pilha<Character> pilha = new Pilha<Character>(getExpressaoInfixa().length);
        int cont = 0;
        char c;

        while (cont < getExpressaoInfixa().length) {
            c = getExpressaoInfixa()[cont];
            switch (c) {
                case '*':
                case '/':
                case '+':
                case '-':
                case '^':
                    while ((!pilha.isEmpty()) && prioridade(c) <= prioridade(pilha.peek())) {
                        posfixa = posfixa.concat(pilha.pop().toString());
                    }
                    pilha.push(c);
                    break;

                case '(':

                    pilha.push(c);

                    break;

                case ')':
                    while (pilha.peek() != '(') {
                        posfixa = posfixa.concat(pilha.pop().toString());
                    }

                    if (pilha.peek() == '(') {

                        pilha.pop();

                    }
                    break;

                default:
                    posfixa = posfixa.concat(getExpressaoInfixa()[cont].toString());
                    break;
            }
            cont++;
        }
        while (!pilha.isEmpty()) {
            if (pilha.peek() != '(') {
                posfixa = posfixa.concat(pilha.pop().toString());
            } else {

                pilha.pop();

            }
        }

        Character[] temp = new Character[this.getExpressaoInfixa().length];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = posfixa.charAt(i);
        }

        this.setExpressaoPosfixa(temp);
    }

    public static int prioridade(char elemento) {
        int prioridade;
        switch (elemento) {
            case '+':
            case '-':
                prioridade = 1;
                break;

            case '*':
            case '/':
                prioridade = 2;
                break;

            case '^':
                prioridade = 3;
                break;

            default:
                prioridade = 0;
                break;
        }

        return prioridade;
    }

    public boolean verificaValidade() throws Exception {
        int count = 0;
        int countpar = 0;
        Character temp;
        while (count < getExpressaoInfixa().length) {
            temp = getExpressaoInfixa()[count];

            if (temp.compareTo('(') == 0) {
                countpar++;
            } else if ((getExpressaoInfixa()[count] >= 48 && getExpressaoInfixa()[count] <= 57) || getExpressaoInfixa()[count] == 40) {
                if (count + 1 < getExpressaoInfixa().length) {
                    if ((getExpressaoInfixa()[count + 1] >= 48 && getExpressaoInfixa()[count + 1] <= 57) || getExpressaoInfixa()[count + 1] == 40) {
                        return false;
                    }
                } else {
                    break;
                }
            } else if (getExpressaoInfixa()[count].compareTo('+') == 0 || getExpressaoInfixa()[count].compareTo('-') == 0 || getExpressaoInfixa()[count].compareTo('*') == 0 || getExpressaoInfixa()[count].compareTo('/') == 0 || getExpressaoInfixa()[count].compareTo('^') == 0) {
                if (count + 1 < getExpressaoInfixa().length) {

                    if (getExpressaoInfixa()[count + 1].compareTo('+') == 0 || getExpressaoInfixa()[count + 1].compareTo('-') == 0 || getExpressaoInfixa()[count + 1].compareTo('*') == 0 || getExpressaoInfixa()[count + 1].compareTo('/') == 0 || getExpressaoInfixa()[count + 1].compareTo('^') == 0) {
                        return false;
                    }
                } else {
                    break;
                }
                if (count + 1 < getExpressaoInfixa().length) {

                    if (!((expressaoInfixa[count + 1] >= 48 && expressaoInfixa[count + 1] <= 57) || expressaoInfixa[count + 1] == 40)) {

                        return false;
                    }
                } else {
                    break;
                }
                if (!((expressaoInfixa[count - 1] >= 48 && expressaoInfixa[count - 1] <= 57) || expressaoInfixa[count - 1] == 40 || expressaoInfixa[count - 1] == 41)) {
                    return false;
                }

            } else if (temp.compareTo(')') == 0) {
                countpar--;
                if (count + 1 < getExpressaoInfixa().length) {
                    if ((getExpressaoInfixa()[count + 1] >= 48 && getExpressaoInfixa()[count + 1] <= 57) || getExpressaoInfixa()[count + 1] == 40) {
                        return false;
                    }
                } else {
                    break;
                }
            }
            count++;
        }

        if (countpar != 0) {
            return false;
        }

        return true;
    }

    public Character[] getExpressaoInfixa() {
        return expressaoInfixa;
    }

    public void setExpressaoInfixa(Character[] expressaoInfixa) {
        this.expressaoInfixa = expressaoInfixa;
    }

    public Character[] getExpressaoPosfixa() {
        return expressaoPosfixa;
    }

    public void setExpressaoPosfixa(Character[] expressaoPosfixa) {
        this.expressaoPosfixa = expressaoPosfixa;
    }

    public String toStringI() {
        String temp = "";

        for (int i = 0; i < this.getExpressaoInfixa().length; i++) {
            temp = temp.concat(this.getExpressaoInfixa()[i].toString());
        }

        return temp;
    }

    public String toStringP() {
        String temp = "";

        for (int i = 0; i < this.getExpressaoPosfixa().length; i++) {
            temp = temp.concat(this.getExpressaoPosfixa()[i].toString());
        }

        return temp;
    }
}
