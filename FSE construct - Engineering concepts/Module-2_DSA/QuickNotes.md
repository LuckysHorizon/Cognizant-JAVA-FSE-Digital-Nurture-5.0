# Data Structures & Algorithms (DSA) Quick Notes

---

# 1. Analysis of Algorithms

## What is an Algorithm?

A finite set of instructions used to solve a problem.

Example:

```java
Find maximum element in an array.
```

---

## Why Data Structures & Algorithms?

### Data Structures
Used to organize and store data efficiently.

### Algorithms
Used to process data efficiently.

### Benefits

- Faster execution
- Better memory utilization
- Scalable applications
- Optimized solutions

Example:

```text
Google Search
Uber Route Finding
Instagram Feed Ranking
```

---

# Types of Data Structures

## Linear Data Structures

Elements stored sequentially.

Examples:

- Array
- Linked List
- Stack
- Queue

---

## Non-Linear Data Structures

Elements stored hierarchically.

Examples:

- Tree
- Graph
- Heap

---

# Time Complexity

Measures execution time growth with input size.

Notation:

```text
n = Input Size
```

Example:

```java
for(int i=0;i<n;i++)
{
    System.out.println(i);
}
```

Time Complexity:

```text
O(n)
```

---

# Space Complexity

Measures extra memory used.

Example:

```java
int[] arr = new int[n];
```

Space Complexity:

```text
O(n)
```

---

# Asymptotic Notations

Used to describe algorithm efficiency.

---

## Big O Notation O()

Upper Bound

Represents worst-case complexity.

Example:

```text
Linear Search = O(n)
```

---

## Omega Ω()

Lower Bound

Represents best-case complexity.

Example:

```text
Linear Search = Ω(1)
```

---

## Theta Θ()

Tight Bound

Represents average/exact growth.

Example:

```text
Merge Sort = Θ(n log n)
```

---

# Growth Order

```text
O(1)
<
O(log n)
<
O(n)
<
O(n log n)
<
O(n²)
<
O(2ⁿ)
<
O(n!)
```

---

# Best, Average and Worst Case

## Best Case

Minimum operations.

Example:

```text
Linear Search
Target found at first position

O(1)
```

---

## Average Case

Expected operations.

Example:

```text
Target found in middle

O(n)
```

---

## Worst Case

Maximum operations.

Example:

```text
Target found at last position

O(n)
```

---

# Finding Time Complexity

## Single Loop

```java
for(int i=0;i<n;i++)
{
}
```

TC:

```text
O(n)
```

---

## Nested Loop

```java
for(int i=0;i<n;i++)
{
    for(int j=0;j<n;j++)
    {
    }
}
```

TC:

```text
O(n²)
```

---

## Logarithmic Loop

```java
for(int i=1;i<n;i*=2)
{
}
```

TC:

```text
O(log n)
```

---

## Recursive Example

```java
fun(n)
{
    fun(n-1);
}
```

TC:

```text
O(n)
```

---

# Sorting Algorithms

---

# Bubble Sort

## Idea

Repeatedly swap adjacent elements.

### Best Case

```text
O(n)
```

### Average Case

```text
O(n²)
```

### Worst Case

```text
O(n²)
```

### Space

```text
O(1)
```

### Stable

✅ Yes

### Use Cases

- Small datasets
- Educational purposes

---

# Insertion Sort

## Idea

Insert element into correct position.

### Best Case

```text
O(n)
```

### Average Case

```text
O(n²)
```

### Worst Case

```text
O(n²)
```

### Space

```text
O(1)
```

### Stable

✅ Yes

### Use Cases

- Nearly sorted data
- Small arrays

---

# Heap Sort

## Idea

Build Max Heap and repeatedly extract maximum.

### Best Case

```text
O(n log n)
```

### Average Case

```text
O(n log n)
```

### Worst Case

```text
O(n log n)
```

### Space

```text
O(1)
```

### Stable

❌ No

### Use Cases

- Priority Queue
- CPU Scheduling
- Top-K Problems

---

# Quick Sort

## Idea

Choose Pivot and Partition.

### Best Case

```text
O(n log n)
```

### Average Case

```text
O(n log n)
```

### Worst Case

```text
O(n²)
```

### Space

```text
O(log n)
```

### Stable

❌ No

### Use Cases

- Database sorting
- Search Engines
- Large in-memory sorting

---

# Merge Sort

## Idea

Divide, Sort, Merge.

### Best Case

```text
O(n log n)
```

### Average Case

```text
O(n log n)
```

### Worst Case

```text
O(n log n)
```

### Space

```text
O(n)
```

### Stable

✅ Yes

### Use Cases

- Large datasets
- External sorting
- Hadoop/Spark processing

---

# Sorting Comparison

| Algorithm | Best | Average | Worst | Space | Stable |
|------------|---------|----------|--------|--------|---------|
| Bubble | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Insertion | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Heap | O(nlogn) | O(nlogn) | O(nlogn) | O(1) | No |
| Quick | O(nlogn) | O(nlogn) | O(n²) | O(logn) | No |
| Merge | O(nlogn) | O(nlogn) | O(nlogn) | O(n) | Yes |

---

# Arrays

## Definition

Collection of same data type stored in contiguous memory.

---

# Array Representation

```text
Index

0 1 2 3 4

Memory

100
104
108
112
116
```

Formula:

```text
Address = Base + (Index × Size)
```

---

# Traversal

Visit every element once.

```java
for(int i=0;i<arr.length;i++)
{
    System.out.println(arr[i]);
}
```

TC:

```text
O(n)
```

---

# Searching in Array

## Linear Search

TC:

```text
Best    O(1)
Average O(n)
Worst   O(n)
```

---

## Binary Search

Requirements:

```text
Array Must Be Sorted
```

TC:

```text
Best    O(1)
Average O(log n)
Worst   O(log n)
```

---

# When to Use Arrays?

Use when:

- Fixed size data
- Fast indexing needed
- Frequent reads

Examples:

```text
Student IDs
Marks List
Monthly Sales
```

---

# Linked List

Nodes connected using pointers.

```text
Data | Next
```

---

# Singly Linked List

```text
10 → 20 → 30 → NULL
```

Operations:

| Operation | Complexity |
|------------|-------------|
| Search | O(n) |
| Insert Beginning | O(1) |
| Insert End | O(n) |
| Delete | O(n) |
| Traverse | O(n) |

---

# Circular Singly Linked List

```text
10 → 20 → 30
↑         ↓
← ← ← ← ←
```

Use Cases:

- Round Robin Scheduling
- Multiplayer Games

---

# Doubly Linked List

```text
NULL ← 10 ⇄ 20 ⇄ 30 → NULL
```

Operations:

| Operation | Complexity |
|------------|-------------|
| Search | O(n) |
| Insert | O(1) |
| Delete | O(1) |
| Traverse | O(n) |

Use Cases:

- Browser History
- Undo/Redo

---

# Circular Doubly Linked List

```text
10 ⇄ 20 ⇄ 30
↑          ↓
← ← ← ← ← ←
```

Use Cases:

- Music Playlist
- Task Scheduling

---

# Linked List Operations

## Search

Find node value.

```text
O(n)
```

---

## Insert

Beginning:

```text
O(1)
```

End:

```text
O(n)
```

---

## Delete

Beginning:

```text
O(1)
```

Specific Node:

```text
O(n)
```

---

## Traverse

```text
O(n)
```

---

# Searching Algorithms

---

# Linear Search

Check every element one by one.

Example:

```text
Array:

10 20 30 40

Find 30
```

TC:

```text
Best    O(1)
Average O(n)
Worst   O(n)
```

SC:

```text
O(1)
```

Use Cases:

- Small datasets
- Unsorted arrays

---

# Binary Search

Works on sorted data.

Example:

```text
10 20 30 40 50 60
```

Find 40.

Middle:

```text
30
```

Discard half repeatedly.

TC:

```text
Best    O(1)
Average O(log n)
Worst   O(log n)
```

SC:

```text
O(1)
```

Use Cases:

- Databases
- Search Engines
- Large Sorted Data

---

# Interview Quick Reminders

## Arrays

```text
Fast Access
O(1) Indexing
```

---

## Linked List

```text
Dynamic Size
Easy Insert/Delete
```

---

## Binary Search

```text
Sorted Data Required
O(log n)
```

---

## Merge Sort

```text
Stable
Guaranteed O(n log n)
```

---

## Quick Sort

```text
Fastest in Practice
Average O(n log n)
```

---

## Heap Sort

```text
Less Memory
Guaranteed O(n log n)
```

---

## Big O Cheat Sheet

```text
O(1)       Constant
O(log n)   Logarithmic
O(n)       Linear
O(n log n) Efficient Sorting
O(n²)      Nested Loops
O(2ⁿ)      Exponential
O(n!)      Factorial
```