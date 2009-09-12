export TESTFILES=./net/sourceforge/javahexeditor
gcj -c -g0 -O2 -v --resource TestsData.hex $TESTFILES/TestsData.hex -o testsdatahex.o
gcj -c -g0 -O2 -v --resource TestsLongData.hex $TESTFILES/TestsLongData.hex -o testslongdatahex.o
gcj -c -g0 -O2 -v --resource TestsUnicode.hex $TESTFILES/TestsUnicode.hex -o testsunicodehex.o
