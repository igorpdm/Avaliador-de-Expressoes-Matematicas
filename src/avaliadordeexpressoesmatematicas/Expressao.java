package avaliadordeexpressoesmatematicas;

public class Expressao {
    
    private Character[] expressaoInfixa;
    private Character[] expressaoPosfixa;

    public Expressao(String exp) {
        
        this.expressaoInfixa = new Character[exp.length()];
        for (int i = 0; i < exp.length(); i++) {
            this.getExpressaoSep()[i] = exp.charAt(i);
        }

    }

    public int calculaValor() {
    try {
        String expressaoPosfixa = convertePosfixa();
        Pilha<Integer> pilha = new Pilha<>(expressaoPosfixa.length());

        for (int i = 0; i < expressaoPosfixa.length(); i++) {
            char c = expressaoPosfixa.charAt(i);

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

    public String convertePosfixa() throws Exception {
        String posfixa = "";
        Pilha<Integer> pilha = new Pilha<Integer>(expressaoInfixa.length);
        int cont = 0;
        char c;
    
        while (cont < expressaoInfixa.length) {
            c = expressaoInfixa[cont];
            switch (c) {
                case '*':
                case '/':
                case '+':
                case '-':
                case '^':
                    while ((!pilha.isEmpty()) && prioridade(c) <= prioridade((char) ((int) pilha.peek()))) {
                        posfixa += (char) ((int) pilha.pop());
                    }
    
                    try {
                        pilha.push((int) c);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
    
                case '(':
                    try {
                        pilha.push((int) c);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
    
                case ')':
                    while ((char) ((int) pilha.peek()) != '(') {
                        posfixa += (char) ((int) pilha.pop());
                    }
    
                    if ((char) ((int) pilha.peek()) == '(') {
                        try {
                            pilha.pop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
    
                default:
                    posfixa += expressaoInfixa[cont];
                    break;
            }
            cont++;
        }
        while (!pilha.isEmpty()) {
            if ((char) ((int) pilha.peek()) != '(') {
                posfixa += (char) ((int) pilha.pop());
            } else {
                try {
                    pilha.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    
        return posfixa;
    }
    
	public static int prioridade(char elemento)
	{
		int prioridade;
		switch(elemento)
		{
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
        while (count < expressaoInfixa.length) {
            temp = expressaoInfixa[count];

            if (temp.compareTo('(') == 0) {
                countpar++;
            } else if ((expressaoInfixa[count] >= 48 && expressaoInfixa[count] <= 57)  || expressaoInfixa[count] == 40) {
                if (count + 1 < expressaoInfixa.length) {
                    if ((expressaoInfixa[count + 1] >= 48 && expressaoInfixa[count + 1] <= 57) || expressaoInfixa[count + 1] == 40) {
                        System.out.println("Essa expressao está invalida");
                        return false;
                    }
                } else {
                    break;
                }
            } else if (expressaoInfixa[count].compareTo('+') == 0 || expressaoInfixa[count].compareTo('-') == 0 || expressaoInfixa[count].compareTo('*') == 0 || expressaoInfixa[count].compareTo('/') == 0 || expressaoInfixa[count].compareTo('^') == 0) {
                if (count + 1 < expressaoInfixa.length) {

                    if (expressaoInfixa[count + 1].compareTo('+') == 0 || expressaoInfixa[count + 1].compareTo('-') == 0 || expressaoInfixa[count + 1].compareTo('*') == 0 || expressaoInfixa[count + 1].compareTo('/') == 0 || expressaoInfixa[count + 1].compareTo('^') == 0) {
                        System.out.println("Essa expressao está invalida");
                        return false;
                    }
                } else {
                    break;
                }
                if (count + 1 < expressaoInfixa.length) {

                    if (!((expressaoInfixa[count + 1] >= 48 && expressaoInfixa[count + 1] <= 57) || expressaoInfixa[count + 1] == 40)) {
                        
                        System.out.println("Essa expressao está invalida");
                        return false;
                    }
                } else {
                    break;
                }
                if (!((expressaoInfixa[count - 1] >= 48 && expressaoInfixa[count - 1] <= 57) || expressaoInfixa[count - 1] == 40 || expressaoInfixa[count - 1] == 41)) {
                    System.out.println("Essa expressao está invalida");
                    return false;
                }

            } else if (temp.compareTo(')') == 0) {
                countpar--;
                if (count + 1 < expressaoInfixa.length) {
                    if ((expressaoInfixa[count + 1] >= 48 && expressaoInfixa[count + 1] <= 57) || expressaoInfixa[count + 1] == 40) {
                        System.out.println("Essa expressao está invalida");
                        return false;
                    }
                } else {
                    break;
                }
            }
            count++;
        }

        if (countpar != 0) {
            System.out.println("Essa expressao está invalida");
            return false;
        }

        return true;
    }
    
 
    @Override
    public String toString(){
        String expFinal = "";
        
        for (int i = 0; i < expressaoInfixa.length; i++) {
            expFinal =  expFinal.concat(expressaoInfixa[i].toString());
        }
        
        return expFinal;
    }
    
    
    
    
    public Character[] getExpressaoSep() {
        return expressaoInfixa;
    }
    public void setExpressaoSep(Character[] expressaoSep) {
        this.expressaoInfixa = expressaoSep;
    }
    public Character[] getExpressaoPosfixo() {
        return expressaoPosfixa;
    }
    public void setExpressaoPosfixo(Character[] expressaoPosfixo) {
        this.expressaoPosfixa = expressaoPosfixo;
    }
}
