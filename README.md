# WordCloud
###### Word Cloud program created from either a file or url. Written in Java.
**by Ronan Connolly**  

![oopAss cover](https://github.com/RonanC/WordCloud/blob/master/graphics/cover.png "oopAss cover")

Contents:
---------
1. About
2. Structure
3. Tools & Environment used
4. Installation
5. Use
6. UML
7. Future Features
8. References
9. Team

1 - About
---
The aim of this project is to read in data from either a local file or url, parse out the words we want *(removing punctuation and html code/tags)*, sort it by how many times the word appears and then draw the words in a word cloud with different, colours and fonts (possibly incorporating a spiral/randomized greedy algorithm for word layout).

Basically what you see as the word cloud above.

Inspiration from [Jonathan Feinberg](http://mrfeinberg.com/), the creator of [Wordle](http://www.wordle.net/).


2 - Structure
---
###Packages
- ie.gmit.sw
- ie.gmit.sw.graphics
- ie.gmit.sw.io
- ie.gmit.sw.test.graphics
- ie.gmit.sw.test.io
- ie.gmit.sw.test.runner

###Design Patterns
- Singleton
- Factory

I created a DataReader factory that produces either Url or File DataReaders.
This factory is a singleton.

###Interface
I created a Gui using Swing components.
The GUI has various options and validation.
When the user presses the "Create Wordcloud" button, it opens a new Gui which is drawn on with the Java Graphics2D API.

###JUnit Tests
I have a test class for most classes.
I have a test suite for each package.
I have a test suite that contains the 3 test suites (for the packages).

###Spiral Algorithm
I created my own spiral algorithm.
Where the word starts in the center (x/y coordinates) and moves to the right with a slight curve down.
It continues this motion until a free space is found.

Randomness is added to move the spiral off course which helps as one spiral will just keep repeating itself.
The default max time to check each words is 2.5 million times. (This should never be reached, only if you have the max words very high possibly).

3 - Tools & Environment used
---
- Eclipse Mars (Version: Mars.1 Release (4.5.1))
- Jsoup Jar (jsoup-1.8.3.jar core library)
- Mac OSX El Capitan (Version 10.11.2)
- Object Aid [Eclipse Plugin] (OMG UML 2.0 standard)


4 - Installation
---
###Run
- Make sure stopwords.txt file is in the same folder as the jar (one word per line).  
- Double click the **wordcloudExecutable.jar** jar to run.
- This jar includes the Jsoup library inside.

OR

- Make sure stopwords.txt file is in the same folder as the jar (one word per line).
- Run the command:
```sh
> java –cp ./wordie.jar ie.gmit.sw.Runner
```
 
- If you get a JSoup error it means you need to add JSoup to your CLASSPATH.

###Dependencies  
- Jsoup (http://jsoup.org/)
- Junit4 (http://search.maven.org/#search|gav|1|g:"junit" AND a:"junit")


5 - Use
---
- Either enter a filename or a url.
- A wordcloud will be draw in front of you.
- You can take a screenshot if you wish to save (there are permission issues when writing locally via Java).
- Click the word cloud to change the background color.


6 - UML
---
*Follows OMG UML 2.0 specification*
###Wordcloud Class Diagram
![Wordcloud Class Diagram](https://github.com/RonanC/WordCloud/blob/master/src/ie/gmit/sw/uml/design.png)

###Test Class Diagram
![Test Class Diagram](https://github.com/RonanC/WordCloud/blob/master/src/ie/gmit/sw/uml/test.png)

7 - Future Features
---
- Implement program (jar) as an applet in a website (java web project).


8 - References
---
- JSoup (http://jsoup.org/)
- Oracle Java 2D Graphics Tutorial (https://docs.oracle.com/javase/tutorial/2d/)
- Object Aid - UML OMG 2.0 - Class Diagram Intro (http://www.objectaid.com/class-diagram)


9 - Team
---
This project was created for the OOP module in GMIT during semester one of fourth year Software Development for Dr. John Healy (lecturer).

<a href="https://github.com/RonanC"><img src="https://github.com/RonanC/DodgySpike/blob/master/PromoImages/Ronan.png" width="100px" height="100px" title="Ronan" alt="Ronan Image"/></a>
