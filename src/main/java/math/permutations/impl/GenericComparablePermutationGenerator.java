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
import java.util.Iterator;

import math.permutations.PermutationGenerator;

/**
 * This is the generic implementation of {@link PermutationGenerator}, where array elements are instances of
 * {@link Comparable}.
 * 
 * @author Stanislav Muhametsin
 * @param <T> The common type of the elements of the permutation array.
 */
public class GenericComparablePermutationGenerator<T extends Comparable<T>> extends
    AbstractGenericPermutationGenerator<T>
{

    public static class GenericComparableArrayInfo<ElementType extends Comparable<ElementType>>
        implements GenericArrayInfo<ElementType>
    {
        private final ElementType[] _array;
        private final int[] _multiplicities;

        public GenericComparableArrayInfo( ElementType[] array )
        {
            Arrays.sort( array );
            this._array = array;

            int distinctElements = this._array.length;
            for( int idx = 0; idx < this._array.length - 1; ++idx )
            {
                if( this._array[idx].compareTo( this._array[idx + 1] ) == 0 )
                {
                    --distinctElements;
                }
            }
            this._multiplicities = new int[distinctElements];
            Arrays.fill( this._multiplicities, 0 );
            int mulIndex = 0;
            for( int idx = 0; idx < this._array.length; ++idx )
            {
                ++this._multiplicities[mulIndex];
                if( idx < this._array.length - 1 && this._array[idx].compareTo( this._array[idx + 1] ) != 0 )
                {
                    ++mulIndex;
                }
            }

        }

        @Override
        public ElementType[] getArray()
        {
            return this._array;
        }

        @Override
        public int getArrayLength()
        {
            return this._array.length;
        }

        @Override
        public int[] getMultiplicities()
        {
            return this._multiplicities;
        }
    }

    public GenericComparablePermutationGenerator( GenericComparableArrayInfo<T> arrayInfo )
    {
        super( arrayInfo );
    }

    @Override
    protected Iterator<T[]> createIterator()
    {
        return new AbstractGenericPermutationIterator<T>( this.getArray(), this.getTotal() )
        {
            @Override
            protected void makeNextPermutation( T[] a )
            {
                T temp = null;

                // Find largest index j with a[j] < a[j+1]
                int j = a.length - 2;
                while( a[j].compareTo( a[j + 1] ) >= 0 )
                {
                    j--;
                }

                // Find index k such that a[k] is smallest integer
                // greater than a[j] to the right of a[j]
                int k = a.length - 1;
                while( a[j].compareTo( a[k] ) >= 0 )
                {
                    k--;
                }

                // Interchange a[j] and a[k]
                temp = a[k];
                a[k] = a[j];
                a[j] = temp;

                // Put tail end of permutation after jth position in increasing order
                int r = a.length - 1;
                int s = j + 1;

                while( r > s )
                {
                    temp = a[s];
                    a[s] = a[r];
                    a[r] = temp;
                    r--;
                    s++;
                }
            }
        };
    }
}