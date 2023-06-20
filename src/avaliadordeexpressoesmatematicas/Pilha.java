package avaliadordeexpressoesmatematicas;

public class Pilha<T> {

    private T[] expressao;
    private int topo;

    public Pilha(int maxtam) {
        this.topo = 0;
        this.expressao = (T[]) new Object[maxtam];
    }

    public void push(T x) throws Exception {
        if (x.equals("")) {
            throw new NullPointerException();
        }
        if (getTopo() == this.getExpressao().length) {
            throw new Exception("Pilha esta cheia!");
        } else {
            getExpressao()[getTopo()] = x;
            setTopo(getTopo() + 1);
        }
    }

    public T pop() throws Exception {
        if (this.getTopo() == 0) {
            throw new Exception("Pilha vazia!");
        } else {
            return getExpressao()[--topo];
        }
    }
    
    public boolean isEmpty() {
        return topo == 0;
    }
    
    public T peek() throws Exception {
        if (this.getTopo() == 0) {
            throw new Exception("Pilha vazia!");
        } else {
            return getExpressao()[topo - 1];
        }
    }

    /**
     * @return the expressao
     */
    public T[] getExpressao() {
        return expressao;
    }

    /**
     * @param expressao the expressao to set
     */
    public void setExpressao(T[] expressao) {
        this.expressao = expressao;
    }

    /**
     * @return the topo
     */
    public int getTopo() {
        return topo;
    }

    /**
     * @param topo the topo to set
     */
    public void setTopo(int topo) {
        this.topo = topo;
    }
}
