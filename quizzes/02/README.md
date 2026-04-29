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

## Status Notes

### Quiz02_01
Status: Solved with testing.

Use dynamic programming from right to left. For each index `i`, scan each
`j > i` once and compute the length of the best nondecreasing subsequence
starting at `i`; ties are broken toward the smaller next index so that the
final answer is the leftmost one. This is `O(n^2)`.

### Quiz02_02
Status: Solved with testing.

Use iterative insertion-sort on the array. No recursion is used.

### Quiz02_03
Status: Solved with testing.

Represent a Game-of-24 state as a list of remaining terms. Each child picks
one pair of terms, combines them with `+`, `-`, `*`, or `/`, and reduces the
number of remaining terms by one. DFS/BFS directly call `DFirstEnumerate` or
`BFirstEnumerate` on this search tree and then filter the leaves whose only
remaining term evaluates to `24`.

### Quiz02_04
Status: Solved with testing.

For `isAVL`, do one postorder traversal and return either the subtree height
or failure. For the maximal-height question, use the minimal-node AVL
recurrence `S(h) = 1 + S(h-1) + S(h-2)` and find the largest `h` with
`S(h) <= 1,000,000`, which gives height `28`.

### Quiz02_05
Status: Solved with testing.

For `isRBT`, do one traversal that checks the red-parent rule and equal black
heights on both subtrees. For the black-height question, use the fact that a
red-black tree of black-height `b` has at most `2^(2b) - 1` internal nodes, so
`1,000,000` keys require black-height `10`.

### Quiz02_06
Status: Solved with testing.

`insert` is standard BST leaf insertion with parent pointers and subtree-size
updates. `reroot` selects a random node by rank using stored sizes, then uses
rotations to move that node to the root.
  
