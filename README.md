cs706-mm
========

Matrix Multiplication for CS 706 GMU


You can add this as a project to eclipse, and then to export a .jar, use the instructions found at bottom of page 19 in labs.pdf. 

all the .txt files are various matrix input files. The input dir contains the same contents as biginput.txt, but split up into multiple files (using 'split -l 10000 biginput.txt')

To generate new matrices, run this 
java -classpath matrixmult.jar mm.GenerateMatrices <M> <N> <P>  > matrixfile.txt

which will create matrix A of size MxN and matrix B of size NxP and write it into matrixfile.txt

