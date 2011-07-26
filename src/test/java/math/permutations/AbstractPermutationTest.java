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

package math.permutations;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.Assert;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class AbstractPermutationTest
{
    protected static interface EqualityTester
    {
        public boolean equals( Object o1, Object o2 );
    }

    protected static class ComparableEqualityTester<T extends Comparable<T>>
        implements EqualityTester
    {
        @Override
        public boolean equals( Object o1, Object o2 )
        {
            return ((T) o1).compareTo( (T) o2 ) == 0;
        }
    }

    protected static class ComparatorEqualityTester<T>
        implements EqualityTester
    {
        private final Comparator<T> _comparator;

        public ComparatorEqualityTester( Comparator<T> comparator )
        {
            this._comparator = comparator;
        }

        @Override
        public boolean equals( Object o1, Object o2 )
        {
            return this._comparator.compare( (T) o1, (T) o2 ) == 0;
        }
    }

    protected static class EqualsMethodTester
        implements EqualityTester
    {
        public static final EqualsMethodTester INSTANCE = new EqualsMethodTester();

        @Override
        public boolean equals( Object o1, Object o2 )
        {
            return o1.equals( o2 );
        }
    }

    protected static class ReferenceTester
        implements EqualityTester
    {

        public static final ReferenceTester INSTANCE = new ReferenceTester();

        @Override
        public boolean equals( Object o1, Object o2 )
        {
            return o1 == o2;
        }
    }

    public static class IntWrapper
    {
        private final Integer _int;

        public IntWrapper( Integer theInt )
        {
            this._int = theInt;
        }

        public Integer getInt()
        {
            return this._int;
        }
    }

    public static class IntWrapperComparator
        implements Comparator<IntWrapper>
    {
        public static final IntWrapperComparator INSTANCE = new IntWrapperComparator();

        @Override
        public int compare( IntWrapper o1, IntWrapper o2 )
        {
            return o1.getInt().compareTo( o2.getInt() );
        }
    }

    public static final IntWrapperComparator WRAPPER_COMPARATOR = new IntWrapperComparator();

    public static final String STRING1 = "item1";
    public static final String STRING2 = "item2";
    public static final String STRING3 = "item3";
    public static final String[] STRING_ARRAY = new String[]
    {
        STRING1, STRING2, STRING3
    };
    public static final String[][] STRING_PERMUTATIONS = new String[][]
    {
        new String[]
        {
            STRING1, STRING2, STRING3
        }, new String[]
        {
            STRING1, STRING3, STRING2
        }, new String[]
        {
            STRING2, STRING1, STRING3
        }, new String[]
        {
            STRING2, STRING3, STRING1
        }, new String[]
        {
            STRING3, STRING1, STRING2
        }, new String[]
        {
            STRING3, STRING2, STRING1
        }
    };

    public static final IntWrapper INT_WRAPPER1 = new IntWrapper( 1 );
    public static final IntWrapper INT_WRAPPER2 = new IntWrapper( 2 );
    public static final IntWrapper INT_WRAPPER3 = new IntWrapper( 3 );
    public static final IntWrapper[] INT_WRAPPER_ARRAY = new IntWrapper[]
    {
        INT_WRAPPER1, INT_WRAPPER2, INT_WRAPPER3
    };
    public static final IntWrapper[][] WRAPPER_PERMUTATIONS = new IntWrapper[][]
    {
        new IntWrapper[]
        {
            INT_WRAPPER1, INT_WRAPPER2, INT_WRAPPER3
        }, new IntWrapper[]
        {
            INT_WRAPPER1, INT_WRAPPER3, INT_WRAPPER2
        }, new IntWrapper[]
        {
            INT_WRAPPER2, INT_WRAPPER1, INT_WRAPPER3
        }, new IntWrapper[]
        {
            INT_WRAPPER2, INT_WRAPPER3, INT_WRAPPER1
        }, new IntWrapper[]
        {
            INT_WRAPPER3, INT_WRAPPER1, INT_WRAPPER2
        }, new IntWrapper[]
        {
            INT_WRAPPER3, INT_WRAPPER2, INT_WRAPPER1
        }
    };

    public static final byte BYTE1 = 1;
    public static final byte BYTE2 = 2;
    public static final byte BYTE3 = 3;
    public static final byte[] BYTE_ARRAY = new byte[]
    {
        BYTE1, BYTE2, BYTE3
    };
    public static final byte[][] BYTE_PERMUTATIONS = new byte[][]
    {
        new byte[]
        {
            BYTE1, BYTE2, BYTE3
        }, new byte[]
        {
            BYTE1, BYTE3, BYTE2
        }, new byte[]
        {
            BYTE2, BYTE1, BYTE3
        }, new byte[]
        {
            BYTE2, BYTE3, BYTE1
        }, new byte[]
        {
            BYTE3, BYTE1, BYTE2
        }, new byte[]
        {
            BYTE3, BYTE2, BYTE1
        }
    };

    public static final int INT1 = 1;
    public static final int INT2 = 2;
    public static final int INT3 = 3;
    public static final int[] INT_ARRAY = new int[]
    {
        INT1, INT2, INT3
    };
    public static final int[][] INT_PERMUTATIONS = new int[][]
    {
        new int[]
        {
            INT1, INT2, INT3
        }, new int[]
        {
            INT1, INT3, INT2
        }, new int[]
        {
            INT2, INT1, INT3
        }, new int[]
        {
            INT2, INT3, INT1
        }, new int[]
        {
            INT3, INT1, INT2
        }, new int[]
        {
            INT3, INT2, INT1
        }
    };

    public static final short SHORT1 = 1;
    public static final short SHORT2 = 2;
    public static final short SHORT3 = 3;
    public static final short[] SHORT_ARRAY = new short[]
    {
        SHORT1, SHORT2, SHORT3
    };
    public static final short[][] SHORT_PERMUTATIONS = new short[][]
    {
        new short[]
        {
            SHORT1, SHORT2, SHORT3
        }, new short[]
        {
            SHORT1, SHORT3, SHORT2
        }, new short[]
        {
            SHORT2, SHORT1, SHORT3
        }, new short[]
        {
            SHORT2, SHORT3, SHORT1
        }, new short[]
        {
            SHORT3, SHORT1, SHORT2
        }, new short[]
        {
            SHORT3, SHORT2, SHORT1
        }
    };

    public static final long LONG1 = 1L;
    public static final long LONG2 = 2L;
    public static final long LONG3 = 3L;
    public static final long[] LONG_ARRAY = new long[]
    {
        LONG1, LONG2, LONG3
    };
    public static final long[][] LONG_PERMUTATIONS = new long[][]
    {
        new long[]
        {
            LONG1, LONG2, LONG3
        }, new long[]
        {
            LONG1, LONG3, LONG2
        }, new long[]
        {
            LONG2, LONG1, LONG3
        }, new long[]
        {
            LONG2, LONG3, LONG1
        }, new long[]
        {
            LONG3, LONG1, LONG2
        }, new long[]
        {
            LONG3, LONG2, LONG1
        }
    };

    public static final double DOUBLE1 = 1.0;
    public static final double DOUBLE2 = 2.0;
    public static final double DOUBLE3 = 3.0;
    public static final double[] DOUBLE_ARRAY = new double[]
    {
        DOUBLE1, DOUBLE2, DOUBLE3
    };
    public static final double[][] DOUBLE_PERMUTATIONS = new double[][]
    {
        new double[]
        {
            DOUBLE1, DOUBLE2, DOUBLE3
        }, new double[]
        {
            DOUBLE1, DOUBLE3, DOUBLE2
        }, new double[]
        {
            DOUBLE2, DOUBLE1, DOUBLE3
        }, new double[]
        {
            DOUBLE2, DOUBLE3, DOUBLE1
        }, new double[]
        {
            DOUBLE3, DOUBLE1, DOUBLE2
        }, new double[]
        {
            DOUBLE3, DOUBLE2, DOUBLE1
        }
    };

    public static final float FLOAT1 = 1.0f;
    public static final float FLOAT2 = 2.0f;
    public static final float FLOAT3 = 3.0f;
    public static final float[] FLOAT_ARRAY = new float[]
    {
        FLOAT1, FLOAT2, FLOAT3
    };
    public static final float[][] FLOAT_PERMUTATIONS = new float[][]
    {
        new float[]
        {
            FLOAT1, FLOAT2, FLOAT3
        }, new float[]
        {
            FLOAT1, FLOAT3, FLOAT2
        }, new float[]
        {
            FLOAT2, FLOAT1, FLOAT3
        }, new float[]
        {
            FLOAT2, FLOAT3, FLOAT1
        }, new float[]
        {
            FLOAT3, FLOAT1, FLOAT2
        }, new float[]
        {
            FLOAT3, FLOAT2, FLOAT1
        }
    };

    protected <ArrayType> void verifyPermutations( PermutationGenerator<ArrayType> generator, ArrayType[] permutations,
        EqualityTester eqTester )
    {
        Iterator<ArrayType> iter = generator.iterator();
        int sequenceIndex = 0;
        while( iter.hasNext() )
        {
            ArrayType nextPermutation = iter.next();
            boolean result = true;
            for( int x = 0; x < Array.getLength( nextPermutation ); ++x )
            {
                result = result
                    && eqTester.equals( Array.get( nextPermutation, x ), Array.get( permutations[sequenceIndex], x ) );
                if( !result )
                {
                    break;
                }
            }

            Assert.assertTrue( "The permutation must be correct one. Got " + this.arrayToString( nextPermutation )
                + ", expected " + this.arrayToString( permutations[sequenceIndex] ) + ".", result );
            ++sequenceIndex;
        }

        Assert.assertEquals( "The amount of permutations must be correct.", permutations.length, sequenceIndex );
    }

    protected String arrayToString( Object array )
    {
        StringBuilder result = new StringBuilder( "[" );
        int arrayLen = Array.getLength( array );
        for( int x = 0; x < arrayLen; ++x )
        {
            result.append( Array.get( array, x ) );
            if( x < arrayLen - 1 )
            {
                result.append( ", " );
            }
        }

        result.append( "]" );
        return result.toString();
    }

    protected <T extends Comparable<T>> PermutationGenerator<T[]> createGenericComparableGenerator1( T... items )
    {
        return PermutationGeneratorProvider.createGenericComparablePermutationGenerator( items );
    }

    protected <T extends Comparable<T>> PermutationGenerator<T[]> createGenericComparableGenerator2(
        Class<T> itemClass, T... items )
    {
        return PermutationGeneratorProvider.createGenericComparablePermutationGenerator( itemClass,
            Arrays.asList( items ) );
    }

    protected <T extends Comparable<T>> PermutationGenerator<T[]> createGenericComparableGenerator3(
        Class<T> itemClass, final T... items )
    {
        return PermutationGeneratorProvider.createGenericComparablePermutationGenerator( itemClass, new Iterable<T>()
        {
            @Override
            public Iterator<T> iterator()
            {
                return Arrays.asList( items ).iterator();
            }
        } );
    }

    protected <T> PermutationGenerator<T[]> createGenericGenerator1( Comparator<T> comparator, T... items )
    {
        return PermutationGeneratorProvider.createGenericPermutationGenerator( comparator, items );
    }

    protected <T> PermutationGenerator<T[]> createGenericGenerator2( Class<T> itemClass, Comparator<T> comparator,
        T... items )
    {
        return PermutationGeneratorProvider.createGenericPermutationGenerator( itemClass, comparator,
            Arrays.asList( items ) );
    }

    protected <T> PermutationGenerator<T[]> createGenericGenerator3( Class<T> itemClass, Comparator<T> comparator,
        final T... items )
    {
        return PermutationGeneratorProvider.createGenericPermutationGenerator( itemClass, comparator, new Iterable<T>()
        {
            @Override
            public Iterator<T> iterator()
            {
                return Arrays.asList( items ).iterator();
            }
        } );
    }

    protected <T> PermutationGenerator<T> createOptimizedGenerator( T array )
    {
        return PermutationGeneratorProvider.createOptimizedGenerator( array );
    }

    protected <T extends Comparable<T>> void runGenericComparableTest1( T[][] correctPermutations, T... items )
    {
        PermutationGenerator<T[]> generator = this.createGenericComparableGenerator1( items );

        this.verifyPermutations( generator, correctPermutations, new ComparableEqualityTester<T>() );
    }

    protected <T extends Comparable<T>> void runGenericComparableTest2( T[][] correctPermutations, Class<T> itemClass,
        T... items )
    {
        PermutationGenerator<T[]> generator = this.createGenericComparableGenerator2( itemClass, items );

        this.verifyPermutations( generator, correctPermutations, new ComparableEqualityTester<T>() );
    }

    protected <T extends Comparable<T>> void runGenericComparableTest3( T[][] correctPermutations, Class<T> itemClass,
        T... items )
    {
        PermutationGenerator<T[]> generator = this.createGenericComparableGenerator3( itemClass, items );

        this.verifyPermutations( generator, correctPermutations, new ComparableEqualityTester<T>() );
    }

    protected <T> void runGenericTest1( T[][] correctPermutations, Comparator<T> comparator, T... items )
    {
        PermutationGenerator<T[]> generator = this.createGenericGenerator1( comparator, items );

        this.verifyPermutations( generator, correctPermutations, new ComparatorEqualityTester<T>( comparator ) );
    }

    protected <T> void runGenericTest2( T[][] correctPermutations, Class<T> itemClass, Comparator<T> comparator,
        T... items )
    {
        PermutationGenerator<T[]> generator = this.createGenericGenerator2( itemClass, comparator, items );

        this.verifyPermutations( generator, correctPermutations, new ComparatorEqualityTester<T>( comparator ) );
    }

    protected <T> void runGenericTest3( T[][] correctPermutations, Class<T> itemClass, Comparator<T> comparator,
        T... items )
    {
        PermutationGenerator<T[]> generator = this.createGenericGenerator3( itemClass, comparator, items );

        this.verifyPermutations( generator, correctPermutations, new ComparatorEqualityTester<T>( comparator ) );
    }

    protected <T> void runOptimizedTest( T[] correctPermutations, T array )
    {
        PermutationGenerator<T> generator = this.createOptimizedGenerator( array );

        this.verifyPermutations( generator, correctPermutations, EqualsMethodTester.INSTANCE );
    }
}
