import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Inmplement a cartesian product without using a for-loop or recursive functions, and without
 * using an array. You can use data structures like a hash map or hash table.
 * 
 * Using any programming language.
 * 
 * Using the best possible data structure and the best possible algorithm. 
 * 
 * Data came from flat text files, sets of tuples r and s are read from r.txt and s.txt.
 * 
 * @author: Sergio Penavades Suarez 
 * 
 * Id: A20387929
 *
 * @version: 10/06/2016/A
*/

public class cartesianProduct {
	
	static Map<Integer, String> rTreeMap = new TreeMap<Integer, String>();//treeMap file r
	static Map<Integer, String> sTreeMap = new TreeMap<Integer, String>();//treeMap file s
	static int count;//count of the treeKey
	
	 /**
     * This function creates a tree map with the data readed from a file.
     * @param file file with the data
     * @param Tree Tree where data will be stored.
     */
	private static void createTreeMap (String file, Map<Integer, String> Tree) throws FileNotFoundException, IOException {
        
		String line;
		count=0;
		
        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);	
        while((line = b.readLine())!=null) {
            String [] splitedLine = line.split(" ");
            for (int i = 0; i < splitedLine.length; i++){
            	count =+ i;
            	Tree.put(count, splitedLine[i]);
            }
        }
        b.close();
    }
	

	/**
    * This function creates the output file if it does not exist or open it and write the cartesian product  
    * @param cartesianProduct It is a string with the value of the last cartesian product.
    */
	private static void writeFile (String cartesianProduct) throws IOException{
		
		BufferedWriter out = null;   
		try {   
		    out = new BufferedWriter(new FileWriter("cartesianProduct.txt", true));    
			out.write(cartesianProduct);   
		} catch (IOException e) {   
			 System.out.println("File wrong"); 
		} finally {   
		    if (out != null) {   
		        out.close();   
		    }   
		} 
	}
	
	/**
	 * This function calculates the cartesian product of the vectors provided (files r.txt and s.txt)
	 */
	private static void product() throws IOException {
		Iterator<Integer> iteratorR = rTreeMap.keySet().iterator();
		while(iteratorR.hasNext()){
		  Integer keyR = (Integer) iteratorR.next();
		  Iterator<Integer> iteratorS = sTreeMap.keySet().iterator();
			while(iteratorS.hasNext()){
			  Integer keyS = (Integer) iteratorS.next();
			  writeFile("<" + rTreeMap.get(keyR) +"," + sTreeMap.get(keyS) + "> ");
			}
		}
	}
	
	/**
	 * This function add a new cartesian product head to the output file
	 */
	private static void cartensianProductHead() throws IOException{
		writeFile("\n-------------------------------------------------------------------\n");
		writeFile(" New Cartesian Product\n");
		writeFile("-------------------------------------------------------------------\n");
	}
	
	

	public static void main (String [] args)  throws IOException {
	
		createTreeMap("./s.txt",sTreeMap);
		createTreeMap("./r.txt", rTreeMap);
		cartensianProductHead();
		product();
		
	}
	
}
