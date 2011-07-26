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

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class IntPermutationGenerator extends AbstractPermutationGenerator<int[]>
{
    public static class IntArrayInfo
        implements ArrayInfo
    {
        private final int[] _array;
        private final int[] _multiplicities;

        public IntArrayInfo( int[] array )
        {
            int[] copy = Arrays.copyOf( array, array.length );
            Arrays.sort( copy );
            this._array = copy;

            int distinctElements = this._array.length;
            for( int idx = 0; idx < this._array.length - 1; ++idx )
            {
                if( this._array[idx] == this._array[idx + 1] )
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
                if( idx < this._array.length - 1 && this._array[idx] != this._array[idx + 1] )
                {
                    ++mulIndex;
                }
            }
        }

        public int[] getArray()
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

    private final int[] _array;

    public IntPermutationGenerator( IntArrayInfo array )
    {
        super( array );

        this._array = array.getArray();
    }

    @Override
    protected Iterator<int[]> createIterator()
    {
        return new AbstractPermutationIterator<int[]>( Arrays.copyOf( this._array, this._array.length ),
            this.getTotal() )
        {
            @Override
            protected void makeNextPermutation( int[] array )
            {
                int temp = 0;

                // Find largest index j with a[j] < a[j+1]
                int j = array.length - 2;
                while( array[j] >= array[j + 1] )
                {
                    j--;
                }

                // Find index k such that a[k] is smallest integer
                // greater than a[j] to the right of a[j]
                int k = array.length - 1;
                while( array[j] >= array[k] )
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
