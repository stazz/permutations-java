/*
 * Copyright (c) 2011, Staz. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package math.permutations;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

/**
 * <p>
 * The algorithm is described by Kenneth H. Rosen, Discrete Mathematics and Its Applications, 2nd edition (NY:
 * McGraw-Hill, 1991), pp. 282-284. Java class example on {@link http://www.merriampark.com/perm.htm} . Generic and
 * optimized versions along with small optimizations by author.
 * </p>
 * 
 * <p>
 * Using this interface it is possible to iterate over all permutations of some array using the enhanced for-loop. The
 * order will always be the same. The methods in {@link PermutationGeneratorProvider} should be used to create instances
 * of {@link PermutationGenerator}.
 * </p>
 * 
 * <p>
 * Each time new permutation generator is created, the given array will be copied using one of
 * {@link Arrays#copyOf(Object[], int)} methods. Additionally, it will be sorted using one of
 * {@link Arrays#sort(Object[])} methods.
 * </p>
 * 
 * <p>
 * Each time a new {@link Iterator} is created, the array will be once again copied using one of
 * {@link Arrays#copyOf(Object[], int)} methods.
 * </p>
 * <p>
 * TODO: null values (for generic permutation generators - is null less or greater than anything else?) This seems to be
 * possible only by providing custom comparator, since Arrays.sort throws on null values.
 * </p>
 * <p>
 * TODO permutation generator for multi-dimensional arrays.
 * </p>
 * 
 * @author Stanislav Muhametsin
 * @param <T> The type of the permutation array.
 * @see PermutationGeneratorProvider
 * 
 **/
public interface PermutationGenerator<T>
    extends Iterable<T>
{
    /**
     * Returns the total amount of permutations for the array given to this permutation generator.
     * 
     * @return The total amount of permutations.
     */
    public BigInteger getTotal();
}
