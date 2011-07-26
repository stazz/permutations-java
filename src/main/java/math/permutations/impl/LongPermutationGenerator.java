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
public class LongPermutationGenerator extends AbstractPermutationGenerator<long[]>
{
    public static class LongArrayInfo
        implements ArrayInfo
    {
        private final long[] _array;
        private final int[] _multiplicities;

        public LongArrayInfo( long[] array )
        {
            long[] copy = Arrays.copyOf( array, array.length );
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

        public long[] getArray()
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

    private final long[] _array;

    public LongPermutationGenerator( LongArrayInfo array )
    {
        super( array );

        this._array = array.getArray();
    }

    @Override
    protected Iterator<long[]> createIterator()
    {
        return new AbstractPermutationIterator<long[]>( Arrays.copyOf( this._array, this._array.length ),
            this.getTotal() )
        {
            @Override
            protected void makeNextPermutation( long[] array )
            {
                long temp = 0L;

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
