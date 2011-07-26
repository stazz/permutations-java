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

package java.math.permutations;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.math.permutations.impl.BytePermutationGenerator;
import java.math.permutations.impl.DoublePermutationGenerator;
import java.math.permutations.impl.FloatPermutationGenerator;
import java.math.permutations.impl.GenericComparablePermutationGenerator;
import java.math.permutations.impl.GenericPermutationGenerator;
import java.math.permutations.impl.IntegerPermutationGenerator;
import java.math.permutations.impl.LongPermutationGenerator;
import java.math.permutations.impl.ShortPermutationGenerator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * This the factory class containing the static methods used to instantiate {@link PermutationGenerator}s. It will use
 * the most optimized implementation of {@link PermutationGenerator} for given item type.
 * 
 * @author 2011 Stanislav Muhametsin
 */
public final class PermutationGeneratorProvider
{

    /**
     * The mapping to hold optimized permutation generator creators.
     */
    private static final Map<Class<?>, OptimizedGeneratorCreator> _optimizedGeneratorCreators;

    /**
     * This is the interface through which the optimized permutation will be created.
     * 
     * @author 2011 Stanislav Muhametsin
     */
    public static interface OptimizedGeneratorCreator
    {
        /**
         * This method will create the optimized {@link PermutationGenerator} for the given array.
         * 
         * @param array The array.
         * @return Optimized {@link PermutationGenerator} for the type of the given array.
         */
        public <ArrayType> PermutationGenerator<ArrayType> createOptimizedGenerator( ArrayType array );
    }

    /**
     * Static constructor will initialize the creators for the optimized permutation generators.
     */
    static
    {
        Map<Class<?>, OptimizedGeneratorCreator> optimizedConstructors = new HashMap<Class<?>, OptimizedGeneratorCreator>();

        optimizedConstructors.put( byte[].class, new OptimizedGeneratorCreator()
        {
            @Override
            public <ArrayType> PermutationGenerator<ArrayType> createOptimizedGenerator( ArrayType array )
            {
                return (PermutationGenerator<ArrayType>) new BytePermutationGenerator( (byte[]) array );
            }
        } );
        optimizedConstructors.put( double[].class, new OptimizedGeneratorCreator()
        {
            @Override
            public <ArrayType> PermutationGenerator<ArrayType> createOptimizedGenerator( ArrayType array )
            {
                return (PermutationGenerator<ArrayType>) new DoublePermutationGenerator( (double[]) array );
            }
        } );
        optimizedConstructors.put( float[].class, new OptimizedGeneratorCreator()
        {
            @Override
            public <ArrayType> PermutationGenerator<ArrayType> createOptimizedGenerator( ArrayType array )
            {
                return (PermutationGenerator<ArrayType>) new FloatPermutationGenerator( (float[]) array );
            }
        } );
        optimizedConstructors.put( int[].class, new OptimizedGeneratorCreator()
        {
            @Override
            public <ArrayType> PermutationGenerator<ArrayType> createOptimizedGenerator( ArrayType array )
            {
                return (PermutationGenerator<ArrayType>) new IntegerPermutationGenerator( (int[]) array );
            }
        } );
        optimizedConstructors.put( long[].class, new OptimizedGeneratorCreator()
        {
            @Override
            public <ArrayType> PermutationGenerator<ArrayType> createOptimizedGenerator( ArrayType array )
            {
                return (PermutationGenerator<ArrayType>) new LongPermutationGenerator( (long[]) array );
            }
        } );
        optimizedConstructors.put( short[].class, new OptimizedGeneratorCreator()
        {
            @Override
            public <ArrayType> PermutationGenerator<ArrayType> createOptimizedGenerator( ArrayType array )
            {
                return (PermutationGenerator<ArrayType>) new ShortPermutationGenerator( (short[]) array );
            }
        } );

        _optimizedGeneratorCreators = optimizedConstructors;
    }

    /**
     * {@link PermutationGeneratorProvider} is not instantiable.
     */
    private PermutationGeneratorProvider()
    {
    }

    public static void registerOptimizedGeneratorCreator( Class<?> arrayClass, OptimizedGeneratorCreator creator )
    {
        _optimizedGeneratorCreators.put( arrayClass, creator );
    }

    /**
     * Creates an optimized version of {@link PermutationGenerator}, if possible. The optimized version will use the
     * array of primitives (eg int[], double[]) instead of array of auto-boxed objects (eg {@link Integer}[],
     * {@link Double[]}).
     * 
     * @param array The array.
     * @return The optimized permutation generator for type of the given array.
     * @exception IllegalArgumentException If the there is no optimized permutation generator for the type of the given
     *                array.
     */
    public static <ArrayType> PermutationGenerator<ArrayType> createOptimizedGenerator( ArrayType array )
    {
        OptimizedGeneratorCreator creator = _optimizedGeneratorCreators.get( array.getClass() );
        if( creator == null )
        {
            throw new IllegalArgumentException( "Could not find optimized permutation generator creator for type "
                + array.getClass() + "." );
        }
        return creator.createOptimizedGenerator( array );
    }

    /**
     * Creates a new permutation generator for given item class.
     * 
     * @param itemClass The common class of the items of permutation array.
     * @param items The permutation items.
     * @return The {@link PermutationGenerator} for given items.
     */
    public static <ItemType extends Comparable<ItemType>> PermutationGenerator<ItemType[]> createGenericPermutationGenerator(
        Class<ItemType> itemClass, ItemType... items )
    {
        // TODO create specific permutation generators for byte, int, short, long, double, float, and use them instead
        // of generic implementation whenever possible.
        return new GenericComparablePermutationGenerator<ItemType>( items );
    }

    /**
     * A helper method for invoking
     * {@link PermutationGeneratorProvider#createGenericPermutationGenerator(Class, Comparable...)} method.
     * 
     * @param itemClass The common class of the items of permutation array.
     * @param items The permutation items.
     * @return The {@link PermutationGenerator} for given items.
     */
    public static <ItemType extends Comparable<ItemType>> PermutationGenerator<ItemType[]> createGenericPermutationGenerator(
        Class<ItemType> itemClass, Collection<ItemType> items )
    {
        ItemType[] array = (ItemType[]) Array.newInstance( itemClass, items.size() );
        items.toArray( array );
        return createGenericPermutationGenerator( itemClass, array );
    }

    /**
     * A helper method for invoking
     * {@link PermutationGeneratorProvider#createGenericPermutationGenerator(Class, Comparable...)} method.
     * 
     * @param itemClass The common class of the items of permutation array.
     * @param items The permutation items.
     * @return The {@link PermutationGenerator} for given items.
     */
    public static <ItemType extends Comparable<ItemType>> PermutationGenerator<ItemType[]> createGenericPermutationGenerator(
        Class<ItemType> itemClass, Iterable<ItemType> items )
    {
        List<ItemType> list = new ArrayList<ItemType>();
        Iterator<ItemType> iter = items.iterator();
        while( iter.hasNext() )
        {
            list.add( iter.next() );
        }

        return createGenericPermutationGenerator( itemClass, list );
    }

    /**
     * Creates a new permutation generator for given item class using given comparator.
     * 
     * @param itemClass The common class of the items of permutation array.
     * @param comparator The comparator to use.
     * @param items The permutation items.
     * @return The {@link PermutationGenerator} for given items.
     */
    public static <ItemType> PermutationGenerator<ItemType[]> createGenericPermutationGenerator(
        Class<ItemType> itemClass, Comparator<ItemType> comparator, ItemType... items )
    {
        // TODO create specific permutation generators for byte, int, short, long, double, float, and use them instead
        // of generic implementation whenever possible.
        return new GenericPermutationGenerator<ItemType>( items, comparator );
    }

    /**
     * A helper method for invoking
     * {@link PermutationGeneratorProvider#createGenericPermutationGenerator(Class, Comparator, Object...)} method.
     * 
     * @param itemClass The common class of the items of permutation array.
     * @param comparator The comparator to use.
     * @param items The permutation items.
     * @return The {@link PermutationGenerator} for given items.
     */
    public static <ItemType> PermutationGenerator<ItemType[]> createGenericPermutationGenerator(
        Class<ItemType> itemClass, Comparator<ItemType> comparator, Collection<ItemType> items )
    {
        ItemType[] array = (ItemType[]) Array.newInstance( itemClass, items.size() );
        items.toArray( array );
        return createGenericPermutationGenerator( itemClass, comparator, array );
    }

    /**
     * A helper method for invoking
     * {@link PermutationGeneratorProvider#createGenericPermutationGenerator(Class, Comparator, Object...)} method.
     * 
     * @param itemClass The common class of the items of permutation array.
     * @param comparator The comparator to use.
     * @param items The permutation items.
     * @return The {@link PermutationGenerator} for given items.
     */
    public static <ItemType> PermutationGenerator<ItemType[]> createGenericPermutationGenerator(
        Class<ItemType> itemClass, Comparator<ItemType> comparator, Iterable<ItemType> items )
    {
        List<ItemType> list = new ArrayList<ItemType>();
        Iterator<ItemType> iter = items.iterator();
        while( iter.hasNext() )
        {
            list.add( iter.next() );
        }

        return createGenericPermutationGenerator( itemClass, comparator, list );
    }
}
