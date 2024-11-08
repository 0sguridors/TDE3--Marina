public class TabelaHash2<K, V> {
    private Node<K, V>[] baldes;
    private int capacidade;
    private int colisoes;
    private int quantidadeNomes;

    public TabelaHash2(int capacidade) {
        this.capacidade = capacidade;
        baldes = new Node[capacidade];
    }

    private int Hash(K chave) {
        int hash = 0;
        int primo = 31;

        if (chave instanceof String) {
            String chaveStr = (String) chave;
            for (int i = 0; i < chaveStr.length(); i++) {
                hash = primo * hash + chaveStr.charAt(i);
            }
        } else {
            hash = chave.hashCode();
        }

        return Math.abs(hash % capacidade);
    }

    public void put(K chave, V valor) {
        quantidadeNomes++;
        int indice = Hash(chave);
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
            System.out.println("Balde " + i + ": " + contador + " elementos");
        }
    }

    public V get(K chave) {
        int indice = Hash(chave);
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
        int indice = Hash(chave);
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
