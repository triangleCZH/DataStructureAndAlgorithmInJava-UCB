/*insertion sort: each time take one more element into consideration and resort the newly added element.(O(n^2)) worst (O(n))best
selection sort: select a smallest from the remainint list and add it back .then remove it and recurse.(O(n^2) avg
merge     sort: devide to half again and again , until only one lement each half, and merge them (so every level merge n elements, with log n levels, we have runtime of nlogn and need n more space) 
quick     sirt: pick a pivot, devide the list into x < pivot, pivot and x > pivot, keeping on recursing on its right and left*/