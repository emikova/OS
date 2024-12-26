package V10;
import java.util.ArrayList;
import java.util.List;

class TreeNode{
    private String name;
    private List<TreeNode> children;
    private TreeNode parent;

    public TreeNode(String name, TreeNode parent){
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public List<TreeNode> getChildren(){
        return children;
    }

    public TreeNode getParent(){
        return parent;
    }

    public void addChild(TreeNode child){
        children.add(child);
    }

    public void removeChild(TreeNode child){
        children.remove(child);
    }

    public TreeNode getChild(String name){
        for (TreeNode child : children){
            if (child.getName().equals(name)){
                return child;

            }
        }
        return null;
    }
}

class DirectoryTree{
    private TreeNode root;

    public DirectoryTree(){
        this.root = new TreeNode("Root", null);
    }

    public void addDirectory(String path){
        String[] components = path.split("/");
        TreeNode current = root;

        for (String component : components){
            TreeNode child = current.getChild(component);
            if (child == null){
                child = new TreeNode(component,current);
                current.addChild(child);
            }
            current = child;
        }
    }

    public void removeDirectory(String path){
        String[] components = path.split("/");
        TreeNode current = root;
        for (String component: components){
            TreeNode child = current.getChild(component);
            if (child == null){
                return;
            }
            current = child;
        }
        current.getParent().removeChild(current);
    }

    public TreeNode findDirectory(String path){
        String[] components = path.split("/");
        TreeNode current = root;

        for (String component: components){
            TreeNode child = current.getChild(component);
            if (child == null){
                return null;
            }
            current = child;
        }
        return current;

    }

    public void printTree(){
        printTree(root,0);
    }

    private void printTree(TreeNode node, int depth){
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++){
            indentation.append("      ");
        }
        System.out.println(indentation + node.getName());

        for (TreeNode child: node.getChildren()){
            printTree(child, depth + 1);
        }
    }
}


public class DirectoryTreeExample {
    public static void main(String[] args) {
        DirectoryTree directoryTree = new DirectoryTree();

        directoryTree.addDirectory("Documents/Projects/Java");
        directoryTree.addDirectory("Documents/Projects/Python");
        directoryTree.addDirectory("Documents/Projects/C");

        System.out.println("Initial Directory Structure: ");
        directoryTree.printTree();

        directoryTree.removeDirectory("Documents/Projects/Python");
        directoryTree.printTree();
    }
}
