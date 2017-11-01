import java.util.Random;

public class AIRandom extends AI {

	Random randomGenerator = new Random();

	private int[] inputs = new int[] {87, 65, 83, 68};// [W, A, S, D]

	public int getKeyInput (GameModel model, boolean logSteps) {
		int randomInput = randomGenerator.nextInt(inputs.length);
	
		if(logSteps) {
			System.out.println(inputs[randomInput]);
		}

		return inputs[randomInput];
	}
}