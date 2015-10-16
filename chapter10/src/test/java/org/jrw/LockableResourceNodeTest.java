package org.jrw;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LockableResourceNodeTest {
    /*
     Tree structure:
                                  root
                               /        \
                       childOne          childTwo
                      /                 /        \
            childThree              childFive    childSix
                                                 /      \
                                         childNine      childTen
     */
    private LockableResourceNode root;
    private LockableResourceNode childOne;
    private LockableResourceNode childTwo;
    private LockableResourceNode childThree;
    private LockableResourceNode childFive;
    private LockableResourceNode childSix;
    private LockableResourceNode childNine;
    private LockableResourceNode childTen;

    @Before
    public void setUp() {
        root = new LockableResourceNode(null);
        childOne = new LockableResourceNode(root);
        childTwo = new LockableResourceNode(root);

        childThree = new LockableResourceNode(childOne);

        childFive = new LockableResourceNode(childTwo);
        childSix = new LockableResourceNode(childTwo);

        childNine = new LockableResourceNode(childSix);
        childTen = new LockableResourceNode(childSix);
    }

    @Test
    public void problem_10_15_isLocked() {
        assertFalse(root.isLocked());
    }

    @Test
    public void problem_10_15_lockRoot() {
        assertTrue(root.lock());
        assertFalse(childTen.isLocked());
        // cannot lock a node when an ancestor is locked
        assertFalse(childTen.lock());
        root.unlock();
        assertTrue(childTen.lock());
    }

    @Test
    public void problem_10_15_lockLeaf() {
        assertTrue(childNine.lock());
        // cannot lock a node when a descendant is locked
        assertFalse(root.lock());
        assertFalse(childSix.lock());
        // can lock a node in an unrelated subtree
        assertTrue(childFive.lock());
        childNine.unlock();
        // can now lock childSix
        assertTrue(childSix.lock());
    }

    @Test
    public void problem_10_15_doubleLocking() {
        assertTrue(childThree.lock());
        assertTrue(childThree.lock());
        assertFalse(childOne.lock());
        childThree.unlock();
        assertTrue(childOne.lock());
    }
}