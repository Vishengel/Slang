import java.util.Random;

public class AIRandom extends AI {

	Random randomGenerator = new Random();

	private int[] inputs = new int[] {87, 65, 83, 68};// [W, A, S, D]

	public int getKeyInput (GameModel model) {

		int randomInput = randomGenerator.nextInt(inputs.length);
		System.out.println(randomInput);
		return inputs[randomInput];
	}
}