# Diferenças entre o meu codigo e o gerado no ChatGPT

### Meu Codigo
- Fiz a minha lista de numeros aleatorios com Array de Inteiros
- Fiz a ordernação crescente dos numeros gerados com um metodo `java.util.Arrays.sort()`

### ChatGPT
- Fez a lista com um Lista de Inteiros
- A ordernação crescente fez com um metodo `java.util.Collections.sort()`

---
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.Random;

    public class RandomNumberGenerator {

        public static void main(String[] args) {
            List<Integer> randomNumbers = Collections.synchronizedList(new ArrayList<>());
            Thread[] threads = new Thread[5];

            for (int i = 0; i < 5; i++) {
                threads[i] = new Thread(new RandomNumberGeneratorThread(randomNumbers));
                threads[i].start();
            }

            try {
                for (int i = 0; i < 5; i++) {
                    threads[i].join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            Collections.sort(randomNumbers);
    
            System.out.println("Números gerados em ordem crescente:");
            for (Integer number : randomNumbers) {
                System.out.println(number);
            }
        }
    }

    class RandomNumberGeneratorThread implements Runnable {
        private List<Integer> randomNumbers;
        private static final int NUM_NUMBERS_TO_GENERATE = 20;
        private static final int MAX_RANDOM_NUMBER = 2000;
    
        public RandomNumberGeneratorThread(List<Integer> randomNumbers) {
            this.randomNumbers = randomNumbers;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < NUM_NUMBERS_TO_GENERATE; i++) {
                int randomNumber = random.nextInt(MAX_RANDOM_NUMBER + 1);
                randomNumbers.add(randomNumber);
            }
        }
    }

**OBS:** Eu não trabalho com Java, logo estou aprendendo as particularidades e potencial do Java.