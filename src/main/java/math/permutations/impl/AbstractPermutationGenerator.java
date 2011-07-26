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

import java.math.BigInteger;
import java.util.Iterator;

import math.permutations.PermutationGenerator;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public abstract class AbstractPermutationGenerator<T>
    implements PermutationGenerator<T>
{
    public static interface ArrayInfo
    {
        public int getArrayLength();

        public int[] getMultiplicities();
    }

    private final BigInteger _total;

    public AbstractPermutationGenerator( ArrayInfo arrayInfo )
    {
        BigInteger upper = getFactorial( arrayInfo.getArrayLength() );
        BigInteger lower = BigInteger.ONE;
        for( int multiplicity : arrayInfo.getMultiplicities() )
        {
            lower = lower.multiply( getFactorial( multiplicity ) );
        }
        this._total = upper.divide( lower );
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
            fact = fact.multiply( BigInteger.valueOf( i ) );
        }
        return fact;
    }

}
