package archive;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HashDinamico{

    private List<List<filme>> buckets; // Lista de listas de filmes que representa os buckets
    private int bucketSize; // Tamanho do bucket
    private int directoryDepth; // Profundidade do diretório

    public HashDinamico(int bucketSize) {
        this.bucketSize = bucketSize;
        this.directoryDepth = 1;
        this.buckets = new ArrayList<>();
        for (int i = 0; i < bucketSize; i++) {
            this.buckets.add(new ArrayList<>()); // Inicializa os buckets como listas vazias
        }
    }

    public void set(filme filme) {
        int key = filme.getId();
        int bucketIndex = calculateBucketIndex(key); // Calcula o índice do bucket para a chave do filme
        List<filme> bucket = buckets.get(bucketIndex); // Obtém o bucket correspondente
        for (int i = 0; i < bucket.size(); i++) {
            filme f = bucket.get(i);
            if (f.getId() == key) { // Se o filme já existe na lista, substitui pelo novo filme
                bucket.set(i, filme);
                return;
            }
        }
        bucket.add(filme); // Caso contrário, adiciona o filme ao final da lista
        if (bucket.size() > bucketSize) { // Se o tamanho do bucket exceder o tamanho máximo permitido
            splitBucket(bucketIndex); // Divide o bucket em dois
        }
    }

    public filme get(int key) {
        int bucketIndex = calculateBucketIndex(key); // Calcula o índice do bucket para a chave
        List<filme> bucket = buckets.get(bucketIndex); // Obtém o bucket correspondente
        for (filme filme : bucket) {
            if (filme.getId() == key) { // Procura o filme na lista do bucket e o retorna se encontrado
                return filme;
            }
        }
        return null; // Retorna null caso o filme não seja encontrado
    }

    public boolean delete(int key) {
        int bucketIndex = calculateBucketIndex(key); // Calcula o índice do bucket para a chave
        List<filme> bucket = buckets.get(bucketIndex); // Obtém o bucket correspondente
        for (int i = 0; i < bucket.size(); i++) {
            filme filme = bucket.get(i);
            if (filme.getId() == key) { // Procura o filme na lista do bucket e o remove se encontrado
                bucket.remove(i);
                return true; // Retorna true indicando que o filme foi removido com sucesso
            }
        }
        return false; // Retorna false indicando que o filme não foi encontrado e não foi removido
    }

    private int calculateBucketIndex(int key) {
        int bucketIndex = key * (int) Math.pow(2, directoryDepth - 1); // Cálculo do índice do bucket
        return bucketIndex % buckets.size(); // Retorna o índice ajustado para o tamanho da lista de buckets
    }

    private void splitBucket(int bucketIndex) {
        List<filme> oldBucket = buckets.get(bucketIndex); // Obtém o bucket que será dividido
        List<filme> newBucket = new ArrayList<>(); // Cria um novo bucket vazio
        buckets.add(newBucket); // Adiciona o novo bucket à lista de buckets
        for (filme filme : oldBucket) {
            int newBucketIndex = calculateBucketIndex(filme.getId());
            if (newBucketIndex != bucketIndex) {
                newBucket.add(filme);
                oldBucket.remove(filme); // Remove o filme do antigo bucket após adicioná-lo ao novo bucket
            }
        }
        if (directoryDepth == Math.ceil(Math.log(buckets.size()) / Math.log(2))) {
            resizeHashTable(); // Verifica se é necessário redimensionar a tabela de hash e, se sim, realiza o redimensionamento
        }
    }
    
    private void resizeHashTable() {
        List<List<filme>> oldBuckets = buckets; // Guarda a referência para a lista antiga de buckets
        directoryDepth++; // Incrementa a profundidade do diretório
        bucketSize *= 2; // Dobra o tamanho dos buckets
        buckets = new ArrayList<>(); // Cria uma nova lista vazia para os buckets redimensionados
        for (int i = 0; i < bucketSize; i++) {
            buckets.add(new ArrayList<>()); // Adiciona novos buckets vazios à lista
        }
        for (List<filme> bucket : oldBuckets) { // Percorre a lista antiga de buckets
            for (filme filme : bucket) { // Percorre os filmes em cada bucket
                set(filme); // Insere novamente cada filme na tabela de hash redimensionada
            }
        }
    }
    
    public void print() {
        try {
            File file = new File("saidas/saidaHash.txt"); // Cria um novo arquivo para escrita
            FileWriter fw = new FileWriter(file); // Cria um FileWriter para escrever no arquivo
            BufferedWriter bw = new BufferedWriter(fw); // Cria um BufferedWriter para escrever no arquivo
            for (int i = 0; i < buckets.size(); i++) { // Percorre os buckets
                bw.write(String.format("Bucket %d:\n", i)); // Escreve o cabeçalho do bucket no arquivo
                List<filme> bucket = buckets.get(i); // Obtém o bucket atual
                for (filme filme : bucket) { // Percorre os filmes no bucket
                    bw.write(String.format("\t%s\n", filme.getId())); // Escreve o ID do filme no arquivo
                }
            }
            bw.close(); // Fecha o BufferedWriter
            fw.close(); // Fecha o FileWriter
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo!");
            e.printStackTrace(); // Imprime a rastreabilidade do erro
        }
    }
}
