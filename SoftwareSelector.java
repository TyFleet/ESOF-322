/* Code is not entirely original. For the sake of instruction Wikipedia's page on
 * strategy pattern algorithms was used. https://en.wikipedia.org/wiki/Strategy_pattern
 * Please attempt to get ahold of myself (Tyler Fleetwood) or Zac Sliker for questions/concerns.
 */

import java.io.File;
import java.util.Scanner;

/*Main to execute and demonstrate the changing of storing strategies as the program executes.*/
public class SoftwareSelector {

	public static void main(String[] args) {
		
		/*First three items are created. Each are subjected to at least one storage method change.
		 * Related is subject to a third to demonstrate more switching*/
        Relational related = new Relational();
        related.applyStrategy();

        NoSQL sequel = new NoSQL();
        sequel.applyStrategy();
        
        Graph nodular = new Graph();
        nodular.applyStrategy();
        
        related.setStrategy(new Node());
        System.out.println("Changing storage mode");
        related.applyStrategy();
        
        sequel.setStrategy(new Table());
        System.out.println("Changing storage mode");
        sequel.applyStrategy();
        
        nodular.setStrategy(new Document());
        System.out.println("Changing storage mode");
        nodular.applyStrategy();
        
        related.setStrategy(new Document());
        System.out.println("Changing storage mode");
        related.applyStrategy();
	}
}
/*Interface to be implemented*/
interface Decision {
	public void Store();
}
/*Encapsulated Algorithms*/
class Table implements Decision{
	static void writeStringtoFile(File file, String data){}
	public void Store(){
		System.out.println("Your current storage is Table");
		writeStringtoFile(new File("C:\\Users\\Tyler\\eclipse-workspace\\ESOFHW2\\Changing.txt"), "This is for tableStore.");
	}
}

class Document implements Decision{
	static void writeStringtoFile(File file, String data){}
	public void Store(){
		System.out.println("Your current storage is document");
		writeStringtoFile(new File("C:\\Users\\Tyler\\eclipse-workspace\\ESOFHW2\\Changing.txt"), "This is for documentStore.");
	}
}

class Node implements Decision{
	static void writeStringtoFile(File file, String data){}
	public void Store(){
		System.out.println("Your current storage is Node");
		writeStringtoFile(new File("C:\\Users\\Tyler\\eclipse-workspace\\ESOFHW2\\Changing.txt"), "This is for nodeStore.");
    }
}
/*Client capable of using algorithms interchangeably. In this case, what strategy we prefer to execute in our data storage*/ 
abstract class Selector{
	private Decision storeStrat;
	
	public Selector(Decision storeStrat) {
		this.storeStrat = storeStrat;
	}
	public void applyStrategy() {
		storeStrat.Store();
	}
	public void setStrategy(Decision storeType) {
		this.storeStrat = storeType;
	}
}
/*Set of clients that use their own algorithms for our data storage*/
class Relational extends Selector{
	public Relational() {
		super(new Table());
	}
}

class NoSQL extends Selector{
	public NoSQL() {
		super(new Document());
	}
}

class Graph extends Selector{
	public Graph() {
		super(new Node());
	}
}
