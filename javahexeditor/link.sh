gcj -v -s -Wl,--subsystem=windows --main=net.sourceforge.javahexeditor.Manager \
-static-libgcj -static-libgcc -o ../bin/javahexeditor-win32_.exe ../bin/hexpng.o -L../bin -ljavahexeditor \
-lswt4javahexeditor ../bin/userguidehtml.o ../bin/swt-so.o
