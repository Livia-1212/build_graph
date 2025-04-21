# build_graph
## TransitiveClosure

A Java console application that lets you define a directed graph of **N** 
nodes and **K** edges, then computes:

- Graph **G<sup>k</sup>** for every path‑length *k* = 1..N
- The **transitive closure** final graph G⁺ (all pairs connected by *any* path)

It prints each G<sup>k</sup> as a matrix of `1` (reachable) and `0` (not reachable), then the final G⁺.

---

## 📋 Features

- Interactive prompts to enter:
    - Number of nodes **N**
    - Number of edges **K**
    - Each edge as a pair `(u v)` in 1‑based indexing
- Boolean‑matrix multiplication to build G², G³, … Gᴺ
- Accumulates results with logical OR into G⁺
- Clear console output of each intermediate graph and final closure

---

## ⚙️ Requirements

- Java 17 or newer (JDK)
- No external libraries or build tools required

---

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/TransitClosure.git
   cd TransitClosure
   ```

2. **Compile**
   ```bash
   javac -d out src/com/drill/TransitiveClosure.java
   ```

3. **Run**
   ```bash
   java -cp out com.drill.TransitiveClosure
   ```

---

## 🖥️ Usage Example

```
Enter number of nodes N: 4
Enter number of edges K: 4
Enter each edge as two integers (u v) between 1 and 4:
1 2
1 3
2 3
3 4

G^1 (paths of length 1):
0 1 1 0
0 0 1 0
0 0 0 1
0 0 0 0

G^2 (paths of length 2):
0 0 1 1
0 0 0 1
0 0 0 0
0 0 0 0

G^3 (paths of length 3):
0 0 0 1
0 0 0 0
0 0 0 0
0 0 0 0

G^4 (paths of length 4):
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0

=== Transitive closure G+ ===
0 1 1 1
0 0 1 1
0 0 0 1
0 0 0 0
```

---

## 🗂️ Project Structure

```
TransitClosure/
├─ src/
│  └─ TransitiveClosure.java
└─ out/                  # compiled .class files (after javac)
```

- **TransitiveClosure.java**
    - `main()` reads user input, builds G, G<sup>k</sup> and G⁺
    - `booleanMatMul` does boolean matrix multiplication
    - `orInto` merges each G<sup>k</sup> into G⁺
    - `copy` and `print` assist with matrix handling and output

---

## 🔧 Customization

- **Change graph input**: Modify the Scanner prompts to accept files or adjacency lists.
- **Alternative output**: Replace the console `print()` with adjacency‑list output or integrate with a graph‑drawing library.
- **Performance**: For large N, consider optimizations or using bitsets for matrix rows.

---

## 📄 License

This project is released under the [MIT License](LICENSE). Feel free to use and adapt!
