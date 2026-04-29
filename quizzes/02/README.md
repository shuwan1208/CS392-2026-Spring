# Quiz 2

It is due Tuesday, the 28th of April, 2026

You can add your own PRIVATE methods to a given
class but you are not allowed to add PUBLIC methods
(except for testing).

The following files are given in Code:

```
Quiz02_00.java
Quiz02_01.java
Quiz02_02.java
Quiz02_03.java
Quiz02_04.java // choose it or Quiz02_05
Quiz02_05.java // choose it or Quiz02_04
Quiz02_06.java
```

Please copy them to MySolution, and you should not add
files of other names.

## Quiz02_00: 20 points

Show your MyLibrary:
For each (top-level) class you implement in MyLibrary,
please create an object of that class.

## Quiz02_01: 50 points

## Quiz02_02: 50 points

## Quiz02_03: 50 points

## Quiz02_04: 30 points + 20 bonus
Please choose this one or Quiz02_05

## Quiz02_05: 30 points + 20 bonus
Please choose this one or Quiz02_04
  
## Quiz02_06: 50 points

##################################################################

## Notes

I chose `Quiz02_04`.

### Quiz02_00
I created one object for each concrete top-level class I wrote in `MyLibrary`.
I did not instantiate interfaces or abstract base classes.

### Quiz02_01
Solved and tested.

I used dynamic programming from right to left. For each position `i`, I check
all `j > i` once and compute the best nondecreasing subsequence starting at
`i`. That gives `O(n^2)` time overall.

### Quiz02_02
Solved and tested.

I used iterative insertion sort only, with no recursion.

### Quiz02_03
Solved and tested.

For Game of 24, I treat each state as the list of terms still left to combine.
At each step I pick two terms, combine them with one operator, and build the
next state with one fewer term. I use the provided tree enumeration methods
directly: `DFirstEnumerate` for DFS and `BFirstEnumerate` for BFS. Then I keep
the leaves whose final term evaluates to `24`.

### Quiz02_04
Solved and tested.

For `isAVL`, I do one postorder traversal. Each call returns the subtree height
if the subtree is valid, and failure otherwise, so each node is visited once.

For the 1,000,000-key AVL question, I used the standard recurrence for the
minimum number of nodes in an AVL tree of height `h`:
`S(h) = 1 + S(h-1) + S(h-2)`.
To make the height as large as possible, the tree should use as few nodes as
possible for each height. The largest height with `S(h) <= 1,000,000` is `28`.


### Quiz02_06
Solved and tested.

`insert` does ordinary BST insertion, updates parent pointers, and fixes the
stored subtree sizes on the path back up. `reroot` picks a random node by rank
using those sizes and rotates it up until it becomes the root.
  
