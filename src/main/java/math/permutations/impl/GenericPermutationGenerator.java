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

    public static class GenericArrayInfoImpl<ElementType>
        implements GenericArrayInfo<ElementType>
    {
        private final ElementType[] _array;
        private final int[] _multiplicities;
        private final Comparator<ElementType> _comparator;

        public GenericArrayInfoImpl( ElementType[] array, Comparator<ElementType> comparator )
        {
            // TODO get rid of copying
            ElementType[] copy = Arrays.copyOf( array, array.length );
            Arrays.sort( copy, comparator );
            this._array = copy;
            this._comparator = comparator;

            int distinctElements = this._array.length;
            for( int idx = 0; idx < this._array.length - 1; ++idx )
            {
                if( comparator.compare( this._array[idx], this._array[idx + 1] ) == 0 )
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
                if( idx < this._array.length - 1 && comparator.compare( this._array[idx], this._array[idx + 1] ) != 0 )
                {
                    ++mulIndex;
                }
            }

        }

        public Comparator<ElementType> getComparator()
        {
            return this._comparator;
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

    private final Comparator<T> _comparator;

    public GenericPermutationGenerator( GenericArrayInfoImpl<T> arrayInfo )
    {
        super( arrayInfo );
        this._comparator = arrayInfo.getComparator();
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
                while( _comparator.compare( array[j], array[j + 1] ) >= 0 )
                {
                    j--;
                }

                // Find index k such that a[k] is smallest integer
                // greater than a[j] to the right of a[j]
                int k = array.length - 1;
                while( _comparator.compare( array[j], array[k] ) >= 0 )
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
