import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class Main{
    public static void main(String[] args) {
        new Main();
    }

    public static void viewTree(MyCollection myCollection){
        String s = "https://dreampuf.github.io/GraphvizOnline/#";
		
		String start = "digraph G{";
		
		String contents = "";
		

		contents = ((BinaryTree)myCollection).getDigraph();
		
		String tail = "}";
				
		String url = s + start + contents + tail;
		url = url.replace(" ", "%20");
		url = url.replace("{", "%7B");
		url = url.replace("}", "%7D");
		url = url.replace("<", "%3C");
		url = url.replace(">", "%3E");
		url = url.replace("\n", "%0D");
		url = url.replace("\"", "%22");
				
		
		Desktop desktop = Desktop.getDesktop();
		try {
            desktop.browse(URI.create(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Main(){
        MyCollection<String> myCollection = new BinaryTree();
        // System.out.println(myCollection.size());
        // myCollection.add("mike");
        // System.out.println(myCollection.size());
        // myCollection.add("golf");
        // // System.out.println(myCollection.size());
        // // myCollection.add("sierra");
        // System.out.println(myCollection.size());
        // myCollection.add("alpha");
        // System.out.println(myCollection.size());


        // System.out.println(myCollection.contains("mike"));
        // System.out.println(myCollection.contains("Haul Truck"));

        myCollection.add("mike");
        myCollection.add("sierra");
        myCollection.add("zulu");

        //Main.viewTree(myCollection);

        


    }

    
}
