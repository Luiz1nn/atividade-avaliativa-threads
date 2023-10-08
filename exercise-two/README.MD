## Diferenças entre o meu codigo e o gerado no ChatGPT

1. Gerenciamento de Threads:
- Na primeira implementação, é criado duas threads `threadOne, threadTwo` e as iniciamos manualmente. Em seguida, usamos o método join para esperar que ambas as threads terminem antes de prosseguir com a ordenação.
- Na segunda implementação, é utilizado um ExecutorService para gerenciar as threads. Isso simplifica o gerenciamento de threads, pois o executor cuida da criação, execução e término das threads. O método awaitTermination é usado para aguardar que todas as threads no executor terminem.

2. Ordenação Merge Sort:
- Na primeira implementação, o algoritmo de ordenação Merge Sort é implementado de forma iterativa usando loops. A função mergeSort divide o vetor em subvetores menores e, em seguida, mescla esses subvetores.
- Na segunda implementação, o algoritmo de ordenação Merge Sort é implementado de forma recursiva. Isso é mais comum na implementação do Merge Sort e é mais legível. A função mergeSort é chamada recursivamente para dividir o vetor em subvetores menores até que cada subvetor tenha apenas um elemento. Em seguida, os subvetores são mesclados recursivamente em um vetor ordenado.