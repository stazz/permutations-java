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

import java.math.BigInteger;
import java.math.permutations.PermutationGenerator;
import java.util.Iterator;


/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public abstract class AbstractPermutationGenerator<T>
    implements PermutationGenerator<T>
{
    private final BigInteger _total;

    public AbstractPermutationGenerator( int arrayLength )
    {
        this._total = getFactorial( arrayLength );
    }

    @Override
    public BigInteger getTotal()
    {
        return this._total;
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.createIterator();
    }

    protected abstract Iterator<T> createIterator();

    private static BigInteger getFactorial( int n )
    {
        BigInteger fact = BigInteger.ONE;
        for( int i = n; i > 1; i-- )
        {
            fact = fact.multiply( new BigInteger( Integer.toString( i ) ) );
        }
        return fact;
    }

}
