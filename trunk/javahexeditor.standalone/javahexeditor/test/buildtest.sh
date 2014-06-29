export SWT_JAR_LIB_NAME="swt-3.5.1-win32-win32-x86"
export PATH_SEPARATOR=";"

gcj -g0 -O2 -s -v -fsource=1.4 -static-libgcj -static-libgcc \
--classpath=../src${PATH_SEPARATOR}../../swt/$SWT_JAR_LIB_NAME/swt.jar${PATH_SEPARATOR}junit3.8.2/junit.jar \
-o ../../bin/test.exe --main=net.sourceforge.javahexeditor.AllTests -L../../bin `find ./net -name "*.java"` \
-ljavahexeditor -ljunit -lswt4javahexeditor ../../bin/swt-so.o ../../bin/testsdatahex.o \
../../bin/testslongdatahex.o ../../bin/testsunicodehex.o
