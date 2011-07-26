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

/**
 * 
 * 
 * @author 2011 Stanislav Muhametsin
 * @param <T>
 */
public abstract class AbstractGenericPermutationGenerator<T> extends AbstractPermutationGenerator<T[]>
{

    private final T[] _array;

    public static interface GenericArrayInfo<ElementType>
        extends ArrayInfo
    {
        public ElementType[] getArray();
    }

    protected static interface DistinctElementCounter<U>
    {
        public int getDistinctElements( U[] array );
    }

    public AbstractGenericPermutationGenerator( GenericArrayInfo<T> arrayInfo )
    {
        super( arrayInfo );
        this._array = arrayInfo.getArray();
    }

    public T[] getArray()
    {
        return this._array;
    }
}
