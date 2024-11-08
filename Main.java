import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TabelaHash1<String, Integer> tabelaHashPrimaria = new TabelaHash1<>(20);
        TabelaHash2<String, Integer> tabelaHashSecundaria = new TabelaHash2<>(20);

        String caminhoArquivo = "C:\\Users\\pohers\\Downloads\\TabelaHASH-main\\female_names.txt";


        long tempoInicioInsercao1 = System.nanoTime();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linhaAtual;
            int indice1 = 1;
            while ((linhaAtual = leitor.readLine()) != null) {
                tabelaHashPrimaria.put(linhaAtual, indice1);
                indice1++;
            }
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo: " + erro.getMessage());
        }
        long tempoFimInsercao1 = System.nanoTime();


        long tempoInicioInsercao2 = System.nanoTime();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linhaAtual;
            int indice2 = 1;
            while ((linhaAtual = leitor.readLine()) != null) {
                tabelaHashSecundaria.put(linhaAtual, indice2);
                indice2++;
            }
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo TXT: " + erro.getMessage());
        }
        long tempoFimInsercao2 = System.nanoTime();


        long tempoInicioBusca1 = System.nanoTime();
        int resultadoBusca1 = tabelaHashPrimaria.get("Ana");
        long tempoFimBusca1 = System.nanoTime();


        long tempoInicioBusca2 = System.nanoTime();
        int resultadoBusca2 = tabelaHashSecundaria.get("Ana");
        long tempoFimBusca2 = System.nanoTime();

        System.out.println("Quantidade de nomes na tabela primária: " + tabelaHashPrimaria.getQuantidadeNomes());
        System.out.println("Número de colisões na tabela primária: " + tabelaHashPrimaria.getColisoes());
        System.out.println("Tempo de inserção na tabela primária: " + (tempoFimInsercao1 - tempoInicioInsercao1) + " ns");
        System.out.println("Tempo de busca pelo nome '" + resultadoBusca1 + "': " + (tempoFimBusca1 - tempoInicioBusca1) + " ns");
        System.out.println();
        System.out.println("Quantidade de nomes na tabela secundária: " + tabelaHashSecundaria.getQuantidadeNomes());
        System.out.println("Número de colisões na tabela secundária: " + tabelaHashSecundaria.getColisoes());
        System.out.println("Tempo de inserção na tabela secundária: " + (tempoFimInsercao2 - tempoInicioInsercao2) + " ns");
        System.out.println("Tempo de busca pelo nome '" + resultadoBusca2 + "': " + (tempoFimBusca2 - tempoInicioBusca2) + " ns");
        System.out.println();

        tabelaHashPrimaria.printDistribution();
        tabelaHashPrimaria.printDistribution();
        System.out.println(" ");
        System.out.println("Tabela Hash 2: ");
        tabelaHashSecundaria.printDistribution();
    }
    }

}
