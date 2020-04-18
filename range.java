import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * The purpose of this code is to read the 
 * coordinates and ranges of the devices from 
 * the input file and base the BFS algorithm from 
 * the first device to the last device, calculate 
 * the hop distance values and output them to 
 * the output.txt file.
 * 
 * */

public class range {
	// The "values" static value will hold how many point inputs there will be.
	static int values;
	// All points will be added to this arraylist.
	static ArrayList<point> list = new ArrayList<point>();

	public static void main(String[] args) {
		try {

			File file = new File("test6.txt"); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			String line;

			int i = 0;

			while ((line = br.readLine()) != null) {
				if (line.charAt(0) != '#') {
					if (i == 0) {
						i = Integer.parseInt(line);
						values = i; // defined values with i which is count of points
					}

					// this loop iterates i times that is count of coordinates
					for (int j = 0; j < i; j++) {
						line = br.readLine(); // reads a line
						String[] LineList = line.split("\t"); // splits the string
						double[] p = new double[3];

						for (int k = 0; k < p.length; k++) {
							p[k] = Double.parseDouble(LineList[k]); // values added p array as double
						}

						point x = new point(p[0], p[1], p[2]); // new object created
						list.add(x); // added object to array List
					}

					findAdjacents(list); // calls findAdjacents function with list
					list.get(0).setDist(0); // distance of first point will be always 0. to do not use ram for
											// unnecessary it sets manually

					ArrayList<point> adjacents = new ArrayList<point>();
					adjacents.add(list.get(0)); // first point is default added to adjacent arrayList
					findDistances(adjacents, 1); // calls with arrayList and default distance is 1 firstly

					for (int j = 0; j < list.size(); j++) { // if distance is not defined still it means this node is
															// not reachable from first node. so distance is 0
						if (list.get(j).getDist() == -1) {
							list.get(j).setDist(0);
						}
					}

					PrintWriter out = new PrintWriter("output.txt", "UTF-8");
					// prints all distance
					for (int j = 0; j < list.size(); j++) {
						out.println(list.get(j).getDist());
					}
					br.close();
					out.close();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This function is called recursively. A loop starts in the number of elements
	 * in the arrayList with each call. inside this cycle, a cycle begins again for
	 * each element. The loop inside is repeated as many times as adjacent to the
	 * element. if necessary, distances are set. There are additions to the list.
	 */
	private static void findDistances(ArrayList<point> adjacents, int i) {
		/*
		 * The distance can never exceed the number of coordinates. if the value of i
		 * exceeds the values, this point could not be reached. and return.
		 */
		if (i > values)
			return;

		int size1 = adjacents.size(); // size1 defined

		for (int j = 0; j < size1; j++) {

			int size2 = adjacents.get(j).getAdj().size(); // size2 defined to not changing. Because there will be adding
															// to list and size shouldn't be changed dynamicly

			for (int k = 0; k < size2; k++) {
				// checks this node not added the list before adds now
				if (!adjacents.contains(adjacents.get(j).getAdj().get(k))) {
					adjacents.add(adjacents.get(j).getAdj().get(k));
					// if distance changed never or found distance is not the best set it
					if (adjacents.get(j).getAdj().get(k).getDist() == -1
							|| adjacents.get(j).getAdj().get(k).getDist() > i) {
						adjacents.get(j).getAdj().get(k).setDist(i);
					}
				}
			}
		}

		// checked nodes removed and call function again
		if (adjacents.size() > 0) {
			adjacents.remove(adjacents.get(0));
			findDistances(adjacents, i + 1);
		}
	}

	/*
	 * This is a call by reference function. Changes made here will be permanent.
	 * Adjacent of all nodes will be added.
	 */
	private static void findAdjacents(ArrayList<point> list) {
		// turns as much as size of list
		for (int i = 0; i < list.size(); i++) {
			// checks all list again to find adjacent
			for (int j = 0; j < list.size(); j++) {
				// itself is not added to list to do not use space and repetition
				if (i != j) {
					// boolean is hold from isAdj function
					boolean availability = isAdj(list.get(i), list.get(j));
					// if available adjacent is added
					if (availability) {
						list.get(i).addAdj(list.get(j));
					}
				}
			}
		}
	}

	/*
	 * The isAdj function compares the distance between two points and radius,
	 * returning whether the connection between two points is available.
	 */
	private static boolean isAdj(point point1, point point2) {
		double xDistance = Math.pow(point1.getxPoint() - point2.getxPoint(), 2);
		double yDistance = Math.pow(point1.getyPoint() - point2.getyPoint(), 2);
		// calculate distance between coordinates
		double distance = Math.pow(xDistance + yDistance, 0.5);
		// range of distance available
		double availableDist = point1.getRadius() + point2.getRadius();

		// checks and return true or false
		if (availableDist >= distance) {
			return true;
		} else {
			return false;
		}
	}
}
