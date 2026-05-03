########################(beg of [Makefile])########################
Library=./../..
###################################################################

comp::

###################################################################

clean:: ; rm -f *~

cleanall:: clean
cleanall:: ; rm -f *.class

###################################################################

comp:: ; javac -cp $(Library) MyMap00.java
comp:: ; javac -cp $(Library) MyMap00FullExn.java
comp:: ; javac -cp $(Library) MyMap00NoKeyExn.java
comp:: ; javac -cp $(Library) MyMap00RBST.java
comp:: ; javac -cp $(Library) MyHashMapSC.java
comp:: ; javac -cp $(Library) MyHashMapQP.java

########################(end of [Makefile])########################
