public class TabelaHash1<K, V> {
    private Node<K, V>[] baldes;
    private int capacidade;
    private int colisoes;
    private int quantidadeNomes;
    
    public TabelaHash1(int capacidade) {
        this.capacidade = capacidade;
        baldes = new Node[capacidade];
    }

    public int hash(K chave) {
        return Math.abs(chave.hashCode() % capacidade);
    }

    public void put(K chave, V valor) {
        quantidadeNomes++;
        int indice = hash(chave);
        Node<K, V> novoNo = new Node<>(chave, valor);

        if (baldes[indice] == null) {
            baldes[indice] = novoNo;
        } else {
            colisoes++;
            Node<K, V> atual = baldes[indice];
            while (true) {
                if (atual.key.equals(chave)) {
                    atual.value = valor;
                    return;
                }
                if (atual.next == null) {
                    atual.next = novoNo;
                    return;
                }
                atual = atual.next;
            }
        }
    }

    public void printDistribution() {
        for (int i = 0; i < capacidade; i++) {
            Node<K, V> atual = baldes[i];
            int contador = 0;
            while (atual != null) {
                contador++;
                atual = atual.next;
            }
            System.out.println("Indice " + i + ": " + contador + " elementos");
        }
    }

    public V get(K chave) {
        int indice = hash(chave);
        Node<K, V> atual = baldes[indice];
        while (atual != null) {
            if (atual.key.equals(chave)) {
                return atual.value;
            }
            atual = atual.next;
        }
        return null;
    }

    public void remove(K chave) {
        int indice = hash(chave);
        Node<K, V> atual = baldes[indice];
        Node<K, V> anterior = null;

        while (atual != null) {
            if (atual.key.equals(chave)) {
                if (anterior == null) {
                    baldes[indice] = atual.next;
                } else {
                    anterior.next = atual.next;
                }
                return;
            }
            anterior = atual;
            atual = atual.next;
        }
    }

    public int getColisoes() {
        return this.colisoes;
    }

    public int getQuantidadeNomes() {
        return this.quantidadeNomes;
    }
}
