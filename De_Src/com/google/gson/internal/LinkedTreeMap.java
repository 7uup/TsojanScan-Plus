/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap<K, V>
extends AbstractMap<K, V>
implements Serializable {
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>(){

        @Override
        public int compare(Comparable a, Comparable b) {
            return a.compareTo(b);
        }
    };
    Comparator<? super K> comparator;
    Node<K, V> root;
    int size = 0;
    int modCount = 0;
    final Node<K, V> header = new Node();
    private EntrySet entrySet;
    private KeySet keySet;

    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator != null ? comparator : NATURAL_ORDER;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public V get(Object key) {
        Node<K, V> node = this.findByObject(key);
        return node != null ? (V)node.value : null;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.findByObject(key) != null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        Node<K, V> created = this.find(key, true);
        Object result = created.value;
        created.value = value;
        return result;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
        ++this.modCount;
        Node<K, V> header = this.header;
        header.prev = header;
        header.next = header.prev;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = this.removeInternalByKey(key);
        return node != null ? (V)node.value : null;
    }

    Node<K, V> find(K key, boolean create) {
        Node<K, V> created;
        Comparator<K> comparator = this.comparator;
        Node<K, V> nearest = this.root;
        int comparison = 0;
        if (nearest != null) {
            Comparable comparableKey = comparator == NATURAL_ORDER ? (Comparable)key : null;
            while (true) {
                Node child;
                int n = comparison = comparableKey != null ? comparableKey.compareTo(nearest.key) : comparator.compare(key, nearest.key);
                if (comparison == 0) {
                    return nearest;
                }
                Node node = child = comparison < 0 ? nearest.left : nearest.right;
                if (child == null) break;
                nearest = child;
            }
        }
        if (!create) {
            return null;
        }
        Node<K, V> header = this.header;
        if (nearest == null) {
            if (comparator == NATURAL_ORDER && !(key instanceof Comparable)) {
                throw new ClassCastException(key.getClass().getName() + " is not Comparable");
            }
            created = new Node<K, V>(nearest, key, header, header.prev);
            this.root = created;
        } else {
            created = new Node<K, V>(nearest, key, header, header.prev);
            if (comparison < 0) {
                nearest.left = created;
            } else {
                nearest.right = created;
            }
            this.rebalance(nearest, true);
        }
        ++this.size;
        ++this.modCount;
        return created;
    }

    Node<K, V> findByObject(Object key) {
        try {
            return key != null ? this.find(key, false) : null;
        } catch (ClassCastException e) {
            return null;
        }
    }

    Node<K, V> findByEntry(Map.Entry<?, ?> entry) {
        Node<K, V> mine = this.findByObject(entry.getKey());
        boolean valuesEqual = mine != null && this.equal(mine.value, entry.getValue());
        return valuesEqual ? mine : null;
    }

    private boolean equal(Object a, Object b) {
        return a == b || a != null && a.equals(b);
    }

    void removeInternal(Node<K, V> node, boolean unlink) {
        if (unlink) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        Node left = node.left;
        Node right = node.right;
        Node originalParent = node.parent;
        if (left != null && right != null) {
            Node adjacent = left.height > right.height ? left.last() : right.first();
            this.removeInternal(adjacent, false);
            int leftHeight = 0;
            left = node.left;
            if (left != null) {
                leftHeight = left.height;
                adjacent.left = left;
                left.parent = adjacent;
                node.left = null;
            }
            int rightHeight = 0;
            right = node.right;
            if (right != null) {
                rightHeight = right.height;
                adjacent.right = right;
                right.parent = adjacent;
                node.right = null;
            }
            adjacent.height = Math.max(leftHeight, rightHeight) + 1;
            this.replaceInParent(node, adjacent);
            return;
        }
        if (left != null) {
            this.replaceInParent(node, left);
            node.left = null;
        } else if (right != null) {
            this.replaceInParent(node, right);
            node.right = null;
        } else {
            this.replaceInParent(node, null);
        }
        this.rebalance(originalParent, false);
        --this.size;
        ++this.modCount;
    }

    Node<K, V> removeInternalByKey(Object key) {
        Node<K, V> node = this.findByObject(key);
        if (node != null) {
            this.removeInternal(node, true);
        }
        return node;
    }

    private void replaceInParent(Node<K, V> node, Node<K, V> replacement) {
        Node parent = node.parent;
        node.parent = null;
        if (replacement != null) {
            replacement.parent = parent;
        }
        if (parent != null) {
            if (parent.left == node) {
                parent.left = replacement;
            } else {
                assert (parent.right == node);
                parent.right = replacement;
            }
        } else {
            this.root = replacement;
        }
    }

    private void rebalance(Node<K, V> unbalanced, boolean insert) {
        Node<K, V> node = unbalanced;
        while (node != null) {
            Node right;
            int rightHeight;
            Node left = node.left;
            int leftHeight = left != null ? left.height : 0;
            int delta = leftHeight - (rightHeight = (right = node.right) != null ? right.height : 0);
            if (delta == -2) {
                Node rightRight;
                int rightRightHeight;
                Node rightLeft = right.left;
                int rightLeftHeight = rightLeft != null ? rightLeft.height : 0;
                int rightDelta = rightLeftHeight - (rightRightHeight = (rightRight = right.right) != null ? rightRight.height : 0);
                if (rightDelta == -1 || rightDelta == 0 && !insert) {
                    this.rotateLeft(node);
                } else {
                    assert (rightDelta == 1);
                    this.rotateRight(right);
                    this.rotateLeft(node);
                }
                if (insert) {
                    break;
                }
            } else if (delta == 2) {
                Node leftRight;
                int leftRightHeight;
                Node leftLeft = left.left;
                int leftLeftHeight = leftLeft != null ? leftLeft.height : 0;
                int leftDelta = leftLeftHeight - (leftRightHeight = (leftRight = left.right) != null ? leftRight.height : 0);
                if (leftDelta == 1 || leftDelta == 0 && !insert) {
                    this.rotateRight(node);
                } else {
                    assert (leftDelta == -1);
                    this.rotateLeft(left);
                    this.rotateRight(node);
                }
                if (insert) {
                    break;
                }
            } else if (delta == 0) {
                node.height = leftHeight + 1;
                if (insert) {
                    break;
                }
            } else {
                assert (delta == -1 || delta == 1);
                node.height = Math.max(leftHeight, rightHeight) + 1;
                if (!insert) break;
            }
            node = node.parent;
        }
    }

    private void rotateLeft(Node<K, V> root) {
        Node left = root.left;
        Node pivot = root.right;
        Node pivotLeft = pivot.left;
        Node pivotRight = pivot.right;
        root.right = pivotLeft;
        if (pivotLeft != null) {
            pivotLeft.parent = root;
        }
        this.replaceInParent(root, pivot);
        pivot.left = root;
        root.parent = pivot;
        root.height = Math.max(left != null ? left.height : 0, pivotLeft != null ? pivotLeft.height : 0) + 1;
        pivot.height = Math.max(root.height, pivotRight != null ? pivotRight.height : 0) + 1;
    }

    private void rotateRight(Node<K, V> root) {
        Node pivot = root.left;
        Node right = root.right;
        Node pivotLeft = pivot.left;
        Node pivotRight = pivot.right;
        root.left = pivotRight;
        if (pivotRight != null) {
            pivotRight.parent = root;
        }
        this.replaceInParent(root, pivot);
        pivot.right = root;
        root.parent = pivot;
        root.height = Math.max(right != null ? right.height : 0, pivotRight != null ? pivotRight.height : 0) + 1;
        pivot.height = Math.max(root.height, pivotLeft != null ? pivotLeft.height : 0) + 1;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        EntrySet result = this.entrySet;
        return result != null ? result : (this.entrySet = new EntrySet());
    }

    @Override
    public Set<K> keySet() {
        KeySet result = this.keySet;
        return result != null ? result : (this.keySet = new KeySet());
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    final class KeySet
    extends AbstractSet<K> {
        KeySet() {
        }

        @Override
        public int size() {
            return LinkedTreeMap.this.size;
        }

        @Override
        public Iterator<K> iterator() {
            return new LinkedTreeMapIterator<K>(){

                @Override
                public K next() {
                    return this.nextNode().key;
                }
            };
        }

        @Override
        public boolean contains(Object o) {
            return LinkedTreeMap.this.containsKey(o);
        }

        @Override
        public boolean remove(Object key) {
            return LinkedTreeMap.this.removeInternalByKey(key) != null;
        }

        @Override
        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    class EntrySet
    extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override
        public int size() {
            return LinkedTreeMap.this.size;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedTreeMapIterator<Map.Entry<K, V>>(){

                @Override
                public Map.Entry<K, V> next() {
                    return this.nextNode();
                }
            };
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Map.Entry && LinkedTreeMap.this.findByEntry((Map.Entry)o) != null;
        }

        @Override
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Node node = LinkedTreeMap.this.findByEntry((Map.Entry)o);
            if (node == null) {
                return false;
            }
            LinkedTreeMap.this.removeInternal(node, true);
            return true;
        }

        @Override
        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    private abstract class LinkedTreeMapIterator<T>
    implements Iterator<T> {
        Node<K, V> next;
        Node<K, V> lastReturned;
        int expectedModCount;

        LinkedTreeMapIterator() {
            this.next = LinkedTreeMap.this.header.next;
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }

        @Override
        public final boolean hasNext() {
            return this.next != LinkedTreeMap.this.header;
        }

        final Node<K, V> nextNode() {
            Node e = this.next;
            if (e == LinkedTreeMap.this.header) {
                throw new NoSuchElementException();
            }
            if (LinkedTreeMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            this.next = e.next;
            this.lastReturned = e;
            return this.lastReturned;
        }

        @Override
        public final void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(this.lastReturned, true);
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }
    }

    static final class Node<K, V>
    implements Map.Entry<K, V> {
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> next;
        Node<K, V> prev;
        final K key;
        V value;
        int height;

        Node() {
            this.key = null;
            this.next = this.prev = this;
        }

        Node(Node<K, V> parent, K key, Node<K, V> next, Node<K, V> prev) {
            this.parent = parent;
            this.key = key;
            this.height = 1;
            this.next = next;
            this.prev = prev;
            prev.next = this;
            next.prev = this;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry other = (Map.Entry)o;
                return (this.key == null ? other.getKey() == null : this.key.equals(other.getKey())) && (this.value == null ? other.getValue() == null : this.value.equals(other.getValue()));
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
        }

        public String toString() {
            return this.key + "=" + this.value;
        }

        public Node<K, V> first() {
            Node<K, V> node = this;
            Node<K, V> child = node.left;
            while (child != null) {
                node = child;
                child = node.left;
            }
            return node;
        }

        public Node<K, V> last() {
            Node<K, V> node = this;
            Node<K, V> child = node.right;
            while (child != null) {
                node = child;
                child = node.right;
            }
            return node;
        }
    }
}

