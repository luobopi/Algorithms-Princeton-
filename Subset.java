
import edu.princeton.cs.introcs.*;

public class Subset {
	
	public static void main(String[ ] args)
    {
        RandomizedQueue<String> str = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            str.enqueue(StdIn.readString());

        for (int i = 0; i < Integer.parseInt(args[0]); i++)
            StdOut.println(str.dequeue());
    }
}
