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

package java.math.permutations.impl;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class DoublePermutationGenerator extends AbstractPermutationGenerator<double[]>
{
    private final double[] _array;

    public DoublePermutationGenerator( double[] array )
    {
        super( array.length );

        this._array = Arrays.copyOf( array, array.length );
        Arrays.sort( this._array );
    }

    @Override
    protected Iterator<double[]> createIterator()
    {
        return new AbstractPermutationIterator<double[]>( this._array, this.getTotal() )
        {
            @Override
            protected void makeNextPermutation( double[] array )
            {
                double temp = 0.0;

                // Find largest index j with a[j] < a[j+1]
                int j = array.length - 2;
                while( array[j] > array[j + 1] )
                {
                    j--;
                }

                // Find index k such that a[k] is smallest integer
                // greater than a[j] to the right of a[j]
                int k = array.length - 1;
                while( array[j] > array[k] )
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
