echo "----- Test $1"
java -classpath . TestVNM tests/$1.in >tests/$1.out
diff -b tests/$1.out tests/$1.expected
