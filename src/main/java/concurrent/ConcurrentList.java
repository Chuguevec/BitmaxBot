package concurrent;

import controller.algo.Algo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentList {
    private List <Algo> list;
    private ExecutorService executorService;

    public ConcurrentList() {
        list = new CopyOnWriteArrayList<>();
        executorService = Executors.newFixedThreadPool(10);
    }

    public void addAlgo(Algo algo){
        list.add(algo);
        executorService.execute(algo);
    }

    public void deleteBot(Algo algo){
        list.remove(algo);
        algo.stop();
    }

    public List<Algo> getAlgoList() {
        return list;
    }
}
