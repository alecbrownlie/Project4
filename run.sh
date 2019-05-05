#!/bin/bash
# run Project 4 Application
rm test/.DS_Store
cd src
export CLASSPATH=$CLASSPATH:lib/jcommon-1.0.23.jar:lib/jfreechart-1.0.19.jar 
javac main/Application.java
java main/Application
cd ..
rm src/*/*.class
