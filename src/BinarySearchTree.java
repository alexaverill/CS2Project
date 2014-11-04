
public class BinarySearchTree {
	private Node root;
	
	private static class Node{
		Node LeftBranch;
		Node RightBranch;
		String dataString;
		Node parent;
		String BranchType; //is this a left or right branch of parent
		
		Node (String stringInput){
			LeftBranch = null;
			RightBranch = null;
			dataString = stringInput;
			
		}
	}
	public void BinarySearchTree(){
		base = null; //Create a blank binary search tree;
	}
	public void insert(Node node, String data){
		if(node == null){
			//if the bst is not yet created
			node = new Node(data); //root
		}
		
	}
}
