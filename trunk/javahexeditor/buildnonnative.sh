gcj -c -g0 -O2 -v -fjni -fsource=1.4 --classpath=../bin -o ../bin/libjavahexeditor.a `find . \! -path "*/plugin/*" \! -path "*/test/*" -name "*.java"`
