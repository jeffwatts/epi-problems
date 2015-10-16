package org.jrw;

/**
 * This class represents a node in a binary tree. Not thread safe.
 *
 * The API provided by this class solves problem 10.15.
 */
public class LockableResourceNode {
    private LockableResourceNode parent;
    private LockableResourceNode leftChild;
    private LockableResourceNode rightChild;
    private boolean isLocked;
    private int lockedDescendantCount;

    public LockableResourceNode(LockableResourceNode parent) {
        this.parent = parent;
        if (parent == null) {
            return;
        }

        if (parent.leftChild == null) {
            parent.leftChild = this;
        } else if (parent.rightChild == null) {
            parent.rightChild = this;
        } else {
            throw new IllegalStateException("left and right children already set on parent");
        }
    }

    public LockableResourceNode getLeftChild() {
        return leftChild;
    }

    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Attempts to lock the passed in Node. Nodes can only be lock if none of their ancestors
     * or descendants are locked
     * @return true if the node was locked; false otherwise
     */
    public boolean lock() {
        if (isLocked()) {
            return true;
        }

        if (lockedDescendantCount > 0) {
            return false;
        }

        // Check whether any ancestors are locked
        LockableResourceNode ancestor = parent;
        while (ancestor != null) {
            if (ancestor.isLocked()) {
                return false;
            }
            ancestor = ancestor.parent;
        }

        isLocked = true;

        // update descendant lock count for all ancestors
        ancestor = parent;
        while (ancestor != null) {
            ancestor.lockedDescendantCount++;
            ancestor = ancestor.parent;
        }

        return true;
    }

    public void unlock() {
        if (!isLocked()) {
            return;
        }

        isLocked = false;

        // Decrement lockedDescendantCount for ancestors
        LockableResourceNode ancestor = parent;
        while (ancestor != null) {
            ancestor.lockedDescendantCount--;
            ancestor = ancestor.parent;
        }
    }
}
