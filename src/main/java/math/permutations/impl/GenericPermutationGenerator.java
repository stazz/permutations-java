/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
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

package math.permutations.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import math.permutations.PermutationGenerator;

/**
 * This is the generic implementation of {@link PermutationGenerator}, where array elements are instances of any object.
 * The comparator must be provided in order to ensure the correct creation of each permutation.
 * 
 * @author 2011 Stanislav Muhametsin
 * @param <T> The common type of the elements of the permutation array.
 */
public class GenericPermutationGenerator<T> extends AbstractGenericPermutationGenerator<T>
{

    private final Comparator<T> _comparator;

    public GenericPermutationGenerator( T[] array, Comparator<T> comparator )
    {
        super( array );
        this._comparator = comparator;
        Arrays.sort( this.getArray(), this._comparator );
    }

    @Override
    protected Iterator<T[]> createIterator()
    {
        return new AbstractGenericPermutationIterator<T>( this.getArray(), this.getTotal() )
        {
            @Override
            protected void makeNextPermutation( T[] array )
            {
                T temp = null;

                // Find largest index j with a[j] < a[j+1]
                int j = array.length - 2;
                while( _comparator.compare( array[j], array[j + 1] ) > 0 )
                {
                    j--;
                }

                // Find index k such that a[k] is smallest integer
                // greater than a[j] to the right of a[j]
                int k = array.length - 1;
                while( _comparator.compare( array[j], array[k] ) > 0 )
                {
                    k--;
                }

                // Interchange a[j] and a[k]
                temp = array[k];
                array[k] = array[j];
                array[j] = temp;

                // Put tail end of permutation after jth position in increasing order
                int r = array.length - 1;
                int s = j + 1;

                while( r > s )
                {
                    temp = array[s];
                    array[s] = array[r];
                    array[r] = temp;
                    r--;
                    s++;
                }
            }
        };
    }
}
