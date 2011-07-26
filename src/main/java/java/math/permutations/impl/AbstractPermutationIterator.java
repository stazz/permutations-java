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

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public abstract class AbstractPermutationIterator<T>
    implements Iterator<T>
{
    private final T _array;
    private final BigInteger _total;
    private BigInteger _permutationsLeft;

    public AbstractPermutationIterator( T array, BigInteger total )
    {
        int arrayLength = Array.getLength( array );
        this._array = (T) Arrays.copyOf( (Object[]) array, arrayLength );
        this._total = total;
        this._permutationsLeft = total;
    }

    @Override
    public boolean hasNext()
    {
        return this._permutationsLeft.compareTo( BigInteger.ZERO ) == 1;
    }

    @Override
    public T next()
    {
        if( !this._permutationsLeft.equals( this._total ) )
        {
            this.makeNextPermutation( this._array );
        }

        this._permutationsLeft = this._permutationsLeft.subtract( BigInteger.ONE );
        return this._array;
    }

    protected abstract void makeNextPermutation( T array );

    /**
     * Always throws {@link UnsupportedOperationException}.
     * 
     * @exception UnsupportedOperationException Always.
     */
    @Override
    public void remove()
    {
        throw new UnsupportedOperationException( "Can not remove permutation." );
    }
}