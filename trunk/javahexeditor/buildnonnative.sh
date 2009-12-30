export SWT_JAR_LIB_NAME="swt-3.5.1-win32-win32-x86"

gcj -c -g0 -O2 -v -fjni -fsource=1.4 --classpath=../swt/$SWT_JAR_LIB_NAME/swt.jar -o ../bin/libjavahexeditor.a `find . \! -path "*/plugin/*" \! -path "*/test/*" -name "*.java"`
