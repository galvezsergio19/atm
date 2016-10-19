set path=%path%;C:\Program Files\Java\jdk1.8.0_20\bin;
set CLASSPATH=C:\Users\sgalvez\Desktop\Sergio\Java\MP\sources;
prompt SERG: $

cls
javac sources/com/acss/smg/model/*.java
javac sources/com/acss/smg/constant/*.java
javac sources/com/acss/smg/dao/*.java
javac sources/com/acss/smg/dao/impl/*.java
javac sources/com/acss/smg/service/*.java
javac sources/com/acss/smg/service/impl/*.java
javac sources/com/acss/smg/utility/*.java
javac sources/com/acss/smg/main/*.java

java com.acss.smg.main.Main 7583765496 hello123@
GOTO END
:END