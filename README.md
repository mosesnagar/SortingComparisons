# **Data Structures – Assignment 5**

##### **IDC, Spring 2019**


### **Introduction:**

In this assignment you will write Java implementation of several algorithms studied in
class:

- Quick sort (with an arbitrary pivot)
- Quick sort (with a random pivot).
- Merge sort
- Counting sort
- Insertion sort (as introduced in the previous assignment)

After completing these functions, you will be able to compare their performances on
different types and sizes of input.

Start by implementing the functions.

When you are satisfied with the operation of your functions, you will be able to run the
main function (also provided) to compare between the performance qualities of the
different algorithms. The runtimes will be graphically depicted for visualization purposes.
In order to view the graphs you will need to add several classes to your project.

### Remarks:

- When implementing quickSortArbitraryPivot choose the pivot, in an arbitrary way, to
    be the rightmost element in the current subarray.
- When implementing the quickSortRandomPivot algorithm, the pivot should be chosen
    random using the random function of Java.
- When implementing the countingSort algorithm, there is no need to make it stable, as
    shown in class. In particular, you are only allowed to use one auxiliary array of size 푘.

Comparisons:

The code provided will execute and plot several comparisons:

- insertion sort vs quick sort on random arrays.
- merge sort vs quick sort on random arrays.
- insertion sort vs quick sort on sorted arrays.
- quicksort with an arbitrary pivot vs quicksort with random pivot on a random array.
- counting sort vs quicksort on integer arrays with elements whose value is smaller
than n, the size of the array.

In each plot, the runtime of each algorithm will be represented by a simple curve. A green
curve representing the function nlog(n) is presented for reference.

The constants at the top of the class determine the input sizes for each comparison. You
should experiment with different sizes to get a feel for how the runtimes behave.
