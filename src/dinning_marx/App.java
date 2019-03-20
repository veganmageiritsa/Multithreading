package dinning_marx;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executorService = null;
		List<Philosopher> philosophers = new ArrayList<>(Constants.PHILOSOPHERS);

		try{

			List<ChopStick> chopsticks = new ArrayList<>(Constants.CHOPSTICKS);
			
			for(int i=0;i<Constants.CHOPSTICKS;i++){
				chopsticks.add(new ChopStick(i));
			}
			
			executorService = Executors.newFixedThreadPool(Constants.PHILOSOPHERS);
			
			for(int i=0;i<Constants.PHILOSOPHERS;i++){
				philosophers.add(new Philosopher(i, chopsticks.get(i), chopsticks.get((i+1) % Constants.PHILOSOPHERS)));
				executorService.execute(philosophers.get(i));
			}
			
			Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

			philosophers.forEach((p)->p.setFull(true));

		}finally{
			
			executorService.shutdown();
			
			while(!executorService.isTerminated()){
				Thread.sleep(1000);
			}

			philosophers.forEach((p)-> System.out.println(p + " ate " + p.getEatingCounter()));


		}
		
	}
}
