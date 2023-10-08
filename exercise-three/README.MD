## Diferenças entre o meu codigo e o gerado no ChatGPT

1. Gerenciamento de Threads:
- Na primeira implementação, é criado manualmente um array de threads e iniciada uma por uma. Em seguida, é usado o método `join` para aguardar que todas as threads terminem.
- Na segunda implementação, é usado um ExecutorService para gerenciar as threads. Isso simplifica o gerenciamento de threads, pois o executor cuida da criação, execução e término das threads. É usado awaitTermination para aguardar que todas as threads terminem.

2. Escalabilidade:
- A primeira implementação cria um número fixo de threads (igual ao número de linhas na matriz). Isso pode ser menos escalável se você quiser lidar com matrizes maiores.
- A segunda implementação cria um número de threads igual ao número de linhas na matriz, mas você pode facilmente ajustar o número de threads alterando o parâmetro passado para `Executors.newFixedThreadPool()`. Isso torna a segunda implementação mais escalável para matrizes maiores.

3. Aguardar a Conclusão das Threads:
- A primeira implementação aguarda a conclusão de cada thread individualmente usando `join` para cada uma delas.
- A segunda implementação usa awaitTermination para aguardar a conclusão de todas as threads criadas pelo ExecutorService.
