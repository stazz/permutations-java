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
import java.util.Arrays;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public abstract class AbstractGenericPermutationIterator<T> extends AbstractPermutationIterator<T[]>
{

    public AbstractGenericPermutationIterator( T[] array, BigInteger total )
    {
        super( Arrays.copyOf( array, array.length ), total );
    }

}
