
public class BinaryTree {
	
	private Node rootNode = null;
	
	public static void main(String[] args) {
		
		BinaryTree bt = new BinaryTree();
		
		bt.addNode(bt.getRootNode(), 10, "Dan");
		bt.addNode(bt.getRootNode(), 2, "Ashley");
		bt.addNode(bt.getRootNode(), 30, "Mike");
		bt.addNode(bt.getRootNode(), 100, "Frank");
		bt.addNode(bt.getRootNode(), 14, "Tim");
		bt.addNode(bt.getRootNode(), 12, "Megan");
		
		//bt.preorder(bt.getRootNode());
		//bt.inorder(bt.getRootNode());
		//bt.postorder(bt.getRootNode());
	}
	
	
	//insert new node. all insertions will create leaf nodes
	public Node addNode(Node focusNode, int index, String info) {
		
		//if root node is null, first set root.
		if(this.getRootNode() == null) {
			Node newNode = new Node(index, info);
			this.setRootNode(newNode);
			return this.getRootNode();
		}
		
		//if the focusNode is null then create new leaf node.
		if(focusNode == null) {
		
			focusNode = new Node(index, info);
			
		} else {
			
			if(focusNode.getIndex() > index) {
				
				//Recursive call. Keep traversing left until you hit null.
				Node newNode = addNode(focusNode.getLeftNode(), index, info);
				
				//Once the null position is found, create the link between the parent node (focusNode) and the new child leaf node.
				focusNode.setLeftNode(newNode);
				
			} else {
				
				//Recursive call. Keep traversing right until you hit null.
				Node newNode = addNode(focusNode.getRightNode(), index, info);
				
				//Once the null position is found, create the link between the parent node (focusNode) and the new child leaf node.
				focusNode.setRightNode(newNode);
			
			}
		}
		
		return focusNode;
	}
	
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}
	
	public Node getRootNode() {
		return this.rootNode;
	}
	
	//preorder traversal
	public void preorder(Node focusNode) {
		if(focusNode != null) {
			System.out.println(focusNode.getIndex());
			preorder(focusNode.getLeftNode());
			preorder(focusNode.getRightNode());
		}
	}
	
	//postorder traversal
	public void postorder(Node focusNode) {
		if(focusNode != null) {
			preorder(focusNode.getLeftNode());
			preorder(focusNode.getRightNode());
			System.out.println(focusNode.getIndex());
		}
	}
	
	//inorder traversal
	public void inorder(Node focusNode) {
		if(focusNode != null) {
			preorder(focusNode.getLeftNode());
			System.out.println(focusNode.getIndex());
			preorder(focusNode.getRightNode());
		}
	}
}


//inner class
class Node {

	private Node rightNode = null;
	private Node leftNode = null;
	
	private int index;
	
	private String info = "";
	
	public Node(int index, String info) {
		this.info = info;
		this.index = index;
	}

	public Node getRightNode() {
		return this.rightNode;
	}

	public Node getLeftNode() {
		return this.leftNode;
	}
	
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	
	public int getIndex() {
		return this.index;
	}

	public String getInfo() {
		return this.info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	public String toString() {
		return "Node [rightNode=" + rightNode + ", leftNode=" + leftNode
				+ ", index=" + index + ", info=" + info + "]";
	}
}
