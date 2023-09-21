package archive;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BTree {

  private Node root;
  private int t; // grau mínimo da árvore

  public BTree(int t) {
    this.t = t;
    this.root = new Node(t);
  }

  // Classe que representa um nó da árvore
  private class Node {

    private int n; // número de chaves presentes no nó
    private boolean leaf; // indica se o nó é uma folha
    private List<Integer> keys; // lista de chaves
    private List<filme> values; // lista de valores
    private List<Node> children; // lista de filhos

    public Node(int t) {
      this.n = 0;
      this.leaf = true;
      this.keys = new ArrayList<>(2 * t - 1);
      this.values = new ArrayList<>(2 * t - 1);
      this.children = new ArrayList<>(2 * t);
    }
  }

  public void insert(filme value) {
    int key = value.getId();
    Node r = this.root;
    if (r.n == 2 * t - 1) {
      Node s = new Node(t);
      this.root = s;
      s.leaf = false;
      s.children.add(r);
      splitChild(s, 0, r);
      insertNonFull(s, key, value);
    } else {
      insertNonFull(r, key, value);
    }
  }

  private void insertNonFull(Node x, int key, filme value) {
    int i = x.n - 1;
    if (x.leaf) {
      // insere no nó folha
      while (i >= 0 && key < x.keys.get(i)) {
        i--;
      }
      x.keys.add(i + 1, key);
      x.values.add(i + 1, value);
      x.n++;
    } else {
      // insere em um nó interno
      while (i >= 0 && key < x.keys.get(i)) {
        i--;
      }
      i++;
      Node child = x.children.get(i);
      if (child.n == 2 * t - 1) {
        splitChild(x, i, child);
        if (key > x.keys.get(i)) {
          i++;
          child = x.children.get(i);
        }
      }
      insertNonFull(child, key, value);
    }
  }

  public Node delet(Node value) {
    return root = delet(root);
  }

  private void splitChild(Node x, int i, Node y) {
    Node z = new Node(t);
    z.leaf = y.leaf;
    z.n = t - 1;
    for (int j = 0; j < t - 1; j++) {
      z.keys.add(j, y.keys.get(j + t));
      z.values.add(j, y.values.get(j + t));
    }
    if (!y.leaf) {
      for (int j = 0; j < t; j++) {
        z.children.add(j, y.children.get(j + t));
      }
    }
    y.n = t - 1;
    x.children.add(i + 1, z);
    x.keys.add(i, y.keys.get(t - 1));
    x.values.add(i, y.values.get(t - 1));
    x.n++;
  }

  public filme search(int key) {
    Node x = root;
    while (x != null) {
      int i = 0;
      while (i < x.n && key > x.keys.get(i)) {
        i++;
      }
      if (i < x.n && key == x.keys.get(i)) {
        return x.values.get(i);
      } else if (x.leaf) {
        return null;
      } else {
        x = x.children.get(i);
      }
    }
    return null;
  }

  public void printBTree() {
    try {
      BufferedWriter writer = new BufferedWriter(
        new FileWriter("saidas/saidaBTree.txt")
      );
      printBTree(root, 0, writer);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void printBTree(Node node, int depth, BufferedWriter writer)
    throws IOException {
    if (node == null) {
      return;
    }
    writer.write("Profundidade " + depth + ": \n");
    for (int i = 0; i < node.n; i++) {
      writer.write(
        "\t\t" + node.keys.get(i) + " - " + node.values.get(i).getName() + "\n"
      );
    }
    writer.write("\n");

    if (!node.leaf) {
      for (int i = 0; i < node.children.size(); i++) {
        Node child = node.children.get(i);
        printBTree(child, depth + 1, writer);
      }
    }
  }
}
