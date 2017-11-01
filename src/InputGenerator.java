import java.util.Random;

public class InputGenerator extends AI {

	Random randomGenerator = new Random();

	private int[] inputs = new int[] {87, 65, 83, 68};// [W, A, S, D]

	public int getKeyInput () {
		int randomInput = randomGenerator.nextInt(inputs.length);
		return inputs[randomInput];
	}
}