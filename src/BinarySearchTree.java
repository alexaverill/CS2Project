
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
		if(root  == null){
			//if the bst is not yet created
			root = new Node(data); //root
		}else{
			Node current = new Node(data);
			//loop through tree
			Node tmp = root;
			while(tmp != null){
				if(current.dataString < tmp.dataString){ //fix string comparisons
					//insert left child
					if(tmp.LeftBranch != null){
						tmp = tmp.LeftBranch;
					}else{
						current.BranchType = "left";
						tmp.LeftBranch = current;
						
					}
				}else if (current.dataString > tmp.dataString){ //fix string comparison
					if(tmp.RightBranch != null){
						tmp = tmp.RightBranch;
					}else{
						current.BranchType = "right";
						tmp.RightBranch = current;
					}
				}
				
			}
		}
		
		
	}
}
