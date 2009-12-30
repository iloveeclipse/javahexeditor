export TESTFILES=./net/sourceforge/javahexeditor
gcj -c -g0 -O2 -v --resource TestsData.hex $TESTFILES/TestsData.hex -o ../../bin/testsdatahex.o
gcj -c -g0 -O2 -v --resource TestsLongData.hex $TESTFILES/TestsLongData.hex -o ../../bin/testslongdatahex.o
gcj -c -g0 -O2 -v --resource TestsUnicode.hex $TESTFILES/TestsUnicode.hex -o ../../bin/testsunicodehex.o
