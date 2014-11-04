
public class BinarySearchTree {
	private Node base;
	
	private static class Node{
		Node LeftBranch;
		Node RightBranch;
		String dataString;
		
		Node (String stringInput){
			LeftBranch = null;
			RightBranch = null;
			dataString = stringInput;
		}
	}
	public void BinarySearchTree(){
		base = null; //Create a blank binary search tree;
	}
	
}
