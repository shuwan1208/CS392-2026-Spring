# Final Exam README

## Final_01
Status: 2. Solved with testing.
Strategy: built a word stream on top of `Final_00.pg2701_char$strmize()`, skipped separators, converted uppercase letters to lowercase, and represented each word as `FnList<Character>`.

## Final_02
Status: 2. Solved with testing.
Strategy: turned the word stream into an array, quicksorted the array lexicographically, compressed equal adjacent words into `(word,count)` pairs, and mergesorted the pair list by descending count and then ascending word.

## Final_03
Status: 2. Solved with testing.
Strategy: used `Assign08_02` as the required open-addressing hash map to count word frequencies, converted the map contents into a `FnList` of pairs, and mergesorted the result by the required order.

## Final_04
Status: 2. Solved with testing.
Strategy: adapted the `Quiz02_06` RBST structure into a generic associative map, used it to count word frequencies, traversed the tree into a list of pairs, and mergesorted the list by the required order.

## Final_05
Status: 2. Solved with testing.
Strategy: used `MyPQueueArray` to implement n-way merge for `LnList` without creating new `LnList` nodes during merge, then used it to build stable 100-way mergesort by splitting a list into 100 consecutive sublists recursively.

## Final_06
Status: 2. Solved with testing.
Strategy: implemented insertion sort without explicit recursion or loops in my code by using `MyLibrary` iteration helpers, binary-searching the insertion position, and shifting the array segment with `System.arraycopy`.
