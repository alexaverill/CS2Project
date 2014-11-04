
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
		root = null; //Create a blank binary search tree;
	}
	public void insert(String data){
		Node newNode = new Node(data);
		Node temp = root;
		
		boolean bRun = true;
		
		if(root  == null){
			//if the bst is not yet created
			root = newNode; //root
		}
		else{
			//loop through tree
			while(temp != null && bRun == true){
				if((newNode.dataString).compareTo(temp.dataString) < 0){ //fix string comparisons
					temp = temp.LeftBranch;
				}
				else if((newNode.dataString).compareTo(temp.dataString) > 0){ //fix string comparison
					temp = temp.RightBranch;
				}
				else if((newNode.dataString).equals(temp.dataString)){
					bRun = false;
				}
			}
			
		if(bRun == true){
			temp = newNode;
		}
		
		}
	}
}
