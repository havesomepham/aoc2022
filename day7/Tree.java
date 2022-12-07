import java.util.*;

public class Tree {

    public Node root;
    public Node currentNode;
    private int rootDirectorySize;

    public Tree(String rootName) {
        this.root = new Node(rootName);
        this.currentNode = this.root;
    }

    public void changeDirectory(String input) throws Exception {
        if (input.equals("..")) { // go back up
            if (this.currentNode.parent == null) {
                throw new Exception("Root Reached");
            }
            this.currentNode = this.currentNode.parent;
        } else {
            this.currentNode = this.currentNode.getChild(input);
        }
    }

    public void setCurrentNodeChild(String name) {
        this.currentNode.setChildNode(new Node(name));
    }

    public void setCurrentNodeChild(String name, int size) {
        this.currentNode.setChildNode(new Node(name, size));
    }

    int validTotal = 0; // total of all directory sizes less than 100000

    // two-fold: finds value of validTotal and directorySize for each directory
    public int collectDirectorySizes(Node node) {
        int directorySize = 0;
        // if node is a file or an empty directory
        if (node.isFile || !node.hasChildren()) {
            return node.size;
        }

        // recursion: the directorySize of the current node is the sum of all of the directorySizes
        // of its children
        for (Node child : node.getChildren()) {
            directorySize += collectDirectorySizes(child);
        }

        if (directorySize <= 100000) {
            validTotal += directorySize;
        }

        return directorySize;
    }

    public int getValidTotal(Node node) {
        rootDirectorySize = collectDirectorySizes(node);
        return validTotal;
    }

    int initUnusedSpace; // amount of unused space if nothing is deleted
    int minSize; // minimum size of deleted directory
    int maxSize = Integer.MAX_VALUE; // smallest directory size larger than minSize

    public int findSmallestDirectory(Node node) {
        int directorySize = 0;
        if (node.isFile || !node.hasChildren()) {
            return node.size;
        }

        for (Node child : node.getChildren()) {
            directorySize += findSmallestDirectory(child);
        }

        if (directorySize > minSize && directorySize < maxSize) {
            maxSize = directorySize;
        }
        return directorySize;
    }

    public int getMaxSize(Node node) {
        initUnusedSpace = 70000000 - rootDirectorySize;
        minSize = 30000000 - initUnusedSpace;
        findSmallestDirectory(node);
        return maxSize;
    }

    public class Node {
        public String name;
        public int size = 0;
        public boolean isFile;
        private Node parent;
        private ArrayList<Node> children = new ArrayList<>();

        // directory
        public Node(String name) {
            this.name = name;
            this.isFile = false;
        }

        // file
        public Node(String name, int size) {
            this.name = name;
            this.size = size;
            this.isFile = true;
        }

        public void setParentNode(Node node) {
            this.parent = node;
        }

        public void setChildNode(Node node) {
            if (!children.contains(node)) {
                children.add(node);
                node.setParentNode(this);
            }
        }

        public Node getParent() {
            return this.parent;
        }

        public Node getChild(String name) {
            for (Node child : children) {
                if (child.name.equals(name)) {
                    return child;
                }
            }
            return null;
        }

        public ArrayList<Node> getChildren() {
            return children;
        }

        public boolean hasChildren() {
            if (children.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
    }
}
