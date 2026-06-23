# Linked Lists Quick Notes

## What is a Linked List?

A Linked List is a linear data structure where elements are stored as nodes. Each node contains data and one or more references (links) to other nodes.

Unlike arrays, linked list nodes are not stored in contiguous memory locations.

### Advantages

- Dynamic size
- Efficient insertion and deletion
- No need for contiguous memory

### Disadvantages

- No direct/random access
- Extra memory required for pointers
- Searching is slower than arrays

---

# 1. Singly Linked List (SLL)

## Description

A Singly Linked List contains nodes where each node stores data and a reference to the next node.

### Structure

```text
10 → 20 → 30 → NULL
```

### Node Structure

```java
class Node {
    int data;
    Node next;
}
```

---

## Operations

### Traversal

Visit each node from head to NULL.

```text
10 → 20 → 30
```

Time Complexity:

```text
O(n)
```

---

### Search

Check nodes one by one until the element is found.

Time Complexity:

```text
Best Case  : O(1)
Average    : O(n)
Worst Case : O(n)
```

---

### Insert

#### Insert at Beginning

```text
NewNode → Head
```

Time Complexity:

```text
O(1)
```

#### Insert at End

Traverse to the last node and attach the new node.

Time Complexity:

```text
O(n)
```

---

### Delete

#### Delete First Node

```text
Head = Head.next
```

Time Complexity:

```text
O(1)
```

#### Delete Last Node

Traverse to second-last node.

Time Complexity:

```text
O(n)
```

---

## Use Cases

- Music queues
- Print queues
- Dynamic memory allocation
- Hash table chaining

---

# 2. Circular Singly Linked List (CSLL)

## Description

A Circular Singly Linked List is similar to a Singly Linked List, except the last node points back to the head node.

### Structure

```text
10 → 20 → 30
↑         ↓
└─────────┘
```

There is no NULL node.

---

## Operations

### Traversal

Continue until the traversal reaches the head again.

Time Complexity:

```text
O(n)
```

---

### Search

Check each node until head is reached again.

Time Complexity:

```text
Best Case  : O(1)
Average    : O(n)
Worst Case : O(n)
```

---

### Insert

#### Insert at Beginning

Time Complexity:

```text
O(1)
```

#### Insert at End

Using Tail Pointer:

```text
O(1)
```

Without Tail Pointer:

```text
O(n)
```

---

### Delete

#### Delete First Node

Time Complexity:

```text
O(1)
```

#### Delete Last Node

Time Complexity:

```text
O(n)
```

---

## Use Cases

- Round Robin CPU Scheduling
- Circular Queues
- Multiplayer Turn-Based Games
- Music Playlists with Repeat Mode

---

# 3. Doubly Linked List (DLL)

## Description

A Doubly Linked List contains two links:

- Previous Node
- Next Node

Allows traversal in both directions.

### Structure

```text
NULL ← 10 ⇄ 20 ⇄ 30 → NULL
```

### Node Structure

```java
class Node {
    int data;
    Node prev;
    Node next;
}
```

---

## Operations

### Traversal

Can move forward and backward.

Time Complexity:

```text
O(n)
```

---

### Search

Search node by traversing from head.

Time Complexity:

```text
Best Case  : O(1)
Average    : O(n)
Worst Case : O(n)
```

---

### Insert

#### Insert at Beginning

Time Complexity:

```text
O(1)
```

#### Insert at End

Using Tail Pointer:

```text
O(1)
```

Without Tail Pointer:

```text
O(n)
```

---

### Delete

#### Delete Known Node

Only pointer updates required.

Time Complexity:

```text
O(1)
```

#### Delete by Value

Search + Delete

Time Complexity:

```text
O(n)
```

---

## Use Cases

- Browser Back and Forward Navigation
- Undo and Redo Operations
- Media Players
- LRU Cache Implementation

---

# 4. Circular Doubly Linked List (CDLL)

## Description

A Circular Doubly Linked List combines features of both:

- Circular Linked List
- Doubly Linked List

The last node points to the first node and the first node points back to the last node.

### Structure

```text
      ┌───────────┐
      ↓           │
10 ⇄ 20 ⇄ 30 ⇄ 40
↑               ↓
└───────────────┘
```

---

## Operations

### Traversal

Forward and backward traversal is possible.

Time Complexity:

```text
O(n)
```

---

### Search

Time Complexity:

```text
Best Case  : O(1)
Average    : O(n)
Worst Case : O(n)
```

---

### Insert

#### Insert at Beginning

```text
O(1)
```

#### Insert at End

```text
O(1)
```

---

### Delete

#### Delete Known Node

```text
O(1)
```

#### Delete by Value

```text
O(n)
```

---

## Use Cases

- Music Players
- Operating System Scheduling
- Image Galleries
- Navigation Systems
- Browser Tabs Management

---

# Time Complexity Comparison

| Operation | SLL | CSLL | DLL | CDLL |
|------------|-----|------|------|------|
| Traverse | O(n) | O(n) | O(n) | O(n) |
| Search | O(n) | O(n) | O(n) | O(n) |
| Insert at Beginning | O(1) | O(1) | O(1) | O(1) |
| Insert at End | O(n) | O(1)* | O(1)* | O(1) |
| Delete First | O(1) | O(1) | O(1) | O(1) |
| Delete Last | O(n) | O(n) | O(1)* | O(1)* |
| Delete Known Node | O(n) | O(n) | O(1) | O(1) |

*Assuming a Tail Pointer is maintained.

---

# Quick Revision

## Singly Linked List

```text
Data → Next
```

Best for simple dynamic data storage.

---

## Circular Singly Linked List

```text
Last Node → Head
```

Best for cyclic processing and scheduling.

---

## Doubly Linked List

```text
Prev ← Data → Next
```

Best for navigation and undo/redo systems.

---

## Circular Doubly Linked List

```text
Prev ← Data → Next
↑                ↓
└────────────────┘
```

Best for playlists, schedulers, and circular navigation systems.

