package avaliadordeexpressoesmatematicas;

public class Fila {

    private Character item[];
    private int inicio, fim;

    public Fila(int maxTam) { // Cria uma Fila vazia
        item = new Character[maxTam];
        inicio = 0;
        fim = inicio;
    }

    public void enfileira(Character item) throws Exception {
        if (item.equals("")) {
            throw new NullPointerException();
        }
        if ((fim + 1) % this.item.length == inicio) {
            throw new Exception("Erro: A fila esta cheia");
        }
        this.item[fim] = item;
        fim = (fim + 1) % this.item.length;

    }

    public Character desenfileira() throws Exception {
        if (inicio == fim) {
            throw new Exception("Erro: A fila esta vazia");
        }
        Character item = this.item[inicio];
        inicio = (inicio + 1) % this.item.length;
        return item;
    }

}
