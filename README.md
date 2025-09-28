Report on Assignment 1

This report summarizes the implementation, analysis, and experimental evaluation of QuickSort, Deterministic Select (Median of Medians), MergeSort, and Closest Pair of Points algorithms.

Architecture Notes

Our implementation includes these four core algorithms with a focus on recursion control and memory optimization.

QuickSort: To control recursion depth, we explicitly track recursive calls and enforced the constraint: depth ≤ 2 * floor(log₂ n) + O(1). This ensures stack safety even in worst-case scenarios.
MergeSort: Memory allocations were minimized through array reuse where possible, specifically with a pre-allocated temporary array in MergeSort.
Deterministic Select (MoM): Implementation focused on an in-place partitioning strategy.
Closest Pair: The divide-and-conquer implementation avoids unnecessary array copying by passing indices instead of creating new subarrays. This optimization minimizes overhead.
Recurrence Analysis

QuickSort:

Uses a randomized pivot strategy for average-case performance.
Recurrence: T(n) = T(k) + T(n-k-1) + Θ(n). Expected depth: Θ(log n), worst-case depth: Θ(n).
Applying Master Theorem intuition, the average runtime is Θ(n log n).
Depth bound was rigorously checked against 2*log₂ n, confirming theoretical expectations under randomization.
MergeSort:

Always splits the array evenly, ensuring consistent behavior.
Recurrence: T(n) = 2T(n/2) + Θ(n).
Using Master Theorem, resolves to Θ(n log n). Memory allocations are Θ(n) due to the auxiliary array.
Depth is exactly log₂ n.
Deterministic Select (Median of Medians):

Designed to guarantee linear worst-case runtime.
Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n).
By Akra-Bazzi analysis, the result is Θ(n).
The method guarantees balanced partitions and prevents QuickSelect’s unlucky deep recursions.
Closest Pair of Points:

Divide-and-conquer approach splits points along the x-axis.
Recurrence: T(n) = 2T(n/2) + Θ(n).
By Master Theorem, the runtime is Θ(n log n).
For small subproblems (n ≤ 2000), a naive Θ(n²) approach is used for validation.
Memory usage is linear, recursion depth is log₂ n.
Plots and Constant-Factor Discussion

We tested arrays of increasing size (n up to ~100,000). Performance was analyzed with time vs. n plots, considering effects of constant factors.

QuickSort performed fastest on average, but recursion depth varied depending on pivot choices. Random pivot was key for performance.
MergeSort had stable performance with predictable depth, but required more memory allocations compared to QuickSort.
Deterministic Select was slower in practice than randomized QuickSelect due to constant factors inherent in the median-of-medians computation.
Closest Pair showed excellent scaling consistent with Θ(n log n), though for small n the O(n²) solution was sometimes faster due to lower constant factors and no recursive overhead.
Practical effects:

Cache locality favored MergeSort over QuickSort in some mid-sized tests where randomized memory access hurt QuickSort.
Java’s garbage collector (GC) introduced minor noise in timings for large n due to temporary arrays allocated in MergeSort’s merging phase.
Summary

Theoretical vs Experimental:

QuickSort and MergeSort both showed Θ(n log n) scaling as predicted.
Deterministic Select behaved as Θ(n), but the higher constants made it slower than built-in Arrays.sort for most practical inputs.
Closest Pair matched Θ(n log n) scaling, validating the divide-and-conquer recurrence.
Alignment: Theory and measurements were consistent in terms of asymptotic growth for all the implemented algorithms. This validated the analysis.

Mismatch: Constant factors (cache behavior, GC activity, and array copying costs) explained minor deviations. Specifically for smaller test sizes with high noise.

Overall, the project confirms the theoretical complexity bounds, illustrating the practical trade-offs in algorithm design within the Java environment. We were able to show memory and depth constraints while minimizing the affect on our performance.
