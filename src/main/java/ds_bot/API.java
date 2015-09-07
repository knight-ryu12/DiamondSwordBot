package ds_bot;

import java.util.Random;

public class API {
	
	 int showRandomInteger(int aStart, int aEnd){
		 if (aStart > aEnd) throw new IllegalArgumentException("Start cannot exceed End.");
		 Random Random = new Random();
	    //get the range, casting to long to avoid overflow problems
	    // compute a fraction of the range, 0 <= frac < range
		 return (int) ((long) ((long) aEnd - (long) aStart + 1 * Random.nextDouble()) + aStart);
	  }
}
